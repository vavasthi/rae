package com.sanjnan.rae.identityserver.services;

import com.sanjnan.rae.common.constants.SanjnanConstants;
import com.sanjnan.rae.common.exception.TokenExpiredException;
import com.sanjnan.rae.common.exception.UnauthorizedException;
import com.sanjnan.rae.common.exception.UnrecoganizedRemoteIPAddressException;
import com.sanjnan.rae.common.utils.H2OPasswordEncryptionManager;
import com.sanjnan.rae.identityserver.data.couchbase.AccountRepository;
import com.sanjnan.rae.identityserver.data.couchbase.SessionRepository;
import com.sanjnan.rae.identityserver.data.couchbase.TenantRepository;
import com.sanjnan.rae.identityserver.pojos.*;
import com.sanjnan.rae.identityserver.security.filters.SanjnanAuthenticationThreadLocal;
import org.apache.commons.codec.binary.Hex;
import org.eclipse.jetty.http.HttpStatus;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by vinay on 2/3/16.
 */
@Service
public class H2OTokenService  {

  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private TenantRepository tenantRepository;
  @Autowired
  private SessionRepository sessionRepository;


  /**
   * This method is called by the authentication filter when token based authentication is performed. This method
   * checks the token cache to find the appropriate token and validate the ip address from which the request had come
   * from. If the ip address could not be validated, the the user is asked to authenticate using username and password.
   *
   * If the token doesn't exist in the cache but is present in the database, then it is populated in the cache.
   *
   * @param tenantDiscriminator discriminator for the tenant
   * @param remoteAddr the ip address from which the incoming request came
   * @param authToken the auth token that needs to be verified.
   * @return token response object.
   * @throws DatatypeConfigurationException
   */
  public H2OUsernameAndTokenResponse contains(String tenantDiscriminator,
                                              String remoteAddr,
                                              String applicationId,
                                              String authToken)
          throws DatatypeConfigurationException {

    return validateAppToken(tenantDiscriminator, remoteAddr, applicationId, authToken);
  }
  public H2OUsernameAndTokenResponse create(String tenantDiscriminator,
                                            String remoteAddr,
                                            String applicationId,
                                            String username,
                                            String password)
          throws DatatypeConfigurationException {

    Optional<Tenant> tenantOptional = tenantRepository.findByDiscriminator(tenantDiscriminator);
    if (tenantOptional.isPresent()) {

      Tenant tenant = tenantOptional.get();
      Optional<Account> accountOptional = accountRepository.findAccountByTenantIdAndAndName(tenant.getId(), username);
      if (accountOptional.isPresent()) {

        Account account = accountOptional.get();
        if (H2OPasswordEncryptionManager.INSTANCE.matches(password, account.getPassword())) {

          UUID sessionId = account.getSessionMap().get(applicationId);
          if (sessionId != null) {
            Optional<Session> sessionOptional = sessionRepository.findById(sessionId);
            if (sessionOptional.isPresent()) {
              Session session = sessionOptional.get();
              deleteToken(tenant, account, session);
              accountOptional = accountRepository.findById(account.getId());
              account = accountOptional.get();
            }
          }
          assignAuthTokenToUser(tenant, account, remoteAddr, applicationId);
        }
        else {
          throw new BadCredentialsException(String.format("%s is not authorized. Credentials mismatch", username));
        }
      }
    }
    throw new BadCredentialsException(remoteAddr);
  }

  /**
   * This method is called when a token needs to be refreshed. The request is validated against the token that
   * is sent as part of the request, a new token is generated and returned.
   *
   *
   * @param tenantDiscriminator discriminator for the tenant
   * @param remoteAddr the ip address from which the incoming request came
   * @param authToken the auth token that needs to be verified.
   * @return token response object.
   * @throws DatatypeConfigurationException
   */
  public H2OUsernameAndTokenResponse refresh(String tenantDiscriminator,
                                             String remoteAddr,
                                             String applicationId,
                                             String authToken)
          throws DatatypeConfigurationException {

    return refreshAppToken(tenantDiscriminator, remoteAddr, applicationId, authToken);
  }

  private H2OUsernameAndTokenResponse validateAppToken(String tenantDiscriminator,
                                                       String remoteAddr,
                                                       String applicationId,
                                                       String authToken) throws DatatypeConfigurationException {

    Optional<Tenant> tenantOptional = tenantRepository.findByDiscriminator(tenantDiscriminator);
    if (tenantOptional.isPresent()) {

      Tenant tenant = tenantOptional.get();
      Optional<Session> sessionOptional = sessionRepository.findByAuthToken(authToken);
      if (sessionOptional.isPresent()) {

        Session session = sessionOptional.get();
        Optional<Account> accountOptional = accountRepository.findById(session.getAccountId());
        if (accountOptional.isPresent()) {

          Account account = accountOptional.get();

          if (session.getApplicationId().equals(applicationId)) {
            if (session.getExpiry().isBefore(new DateTime())) {

              throw new TokenExpiredException(HttpStatus.UNAUTHORIZED_401, authToken + " is expired.");
            }
            Set<String> remoteAddresses = account.getRemoteAddresses().stream().collect(Collectors.toSet());
            if (!remoteAddresses.contains(remoteAddr)) {

              throw new UnauthorizedException("Unknown IP address, please reauthenticate." + remoteAddr);
            }
            SanjnanAuthenticationThreadLocal.INSTANCE.initializeThreadLocals(tenant, account, session);
            return new H2OUsernameAndTokenResponse(tenantDiscriminator,
                    account.getName(),
                    new H2OTokenResponse(authToken,
                            account.getComputeRegion(),
                            session.getExpiry(),
                            account.getH2ORoles()));
          }
        }
      } else {

        throw new UnauthorizedException(String.format("Token %s doesn't belong to application %s", authToken, applicationId));
      }
    }
    throw new BadCredentialsException(tenantDiscriminator + "/" + remoteAddr);
  }

  private H2OUsernameAndTokenResponse refreshAppToken(String tenantDiscriminator,
                                                      String remoteAddr,
                                                      String applicationId,
                                                      String authToken)
          throws DatatypeConfigurationException {

    Optional<Tenant> tenantOptional = tenantRepository.findByDiscriminator(tenantDiscriminator);
    if (tenantOptional.isPresent()) {
      Tenant tenant = tenantOptional.get();
      Optional<Session> sessionOptional = sessionRepository.findByAuthToken(authToken);
      if (sessionOptional.isPresent()) {

        Session session = sessionOptional.get();
        Optional<Account> accountOptional = accountRepository.findById(session.getAccountId());

        if (accountOptional.isPresent()) {
          Account account = accountOptional.get();
          return assignAuthTokenToUser(tenant, account, session, account.getName());
        }
        else {

          throw new UnauthorizedException(String.format("Token %s doesn't belong to application %s", authToken, applicationId));
        }
      }

    }
    throw new BadCredentialsException(tenantDiscriminator + "/" + remoteAddr);
  }
  public H2OUsernameAndTokenResponse assignAuthTokenToUser(Tenant tenant,
                                                           Account account,
                                                           String remoteAddr,
                                                           String applicationId)
          throws DatatypeConfigurationException {
    if (!account.getRemoteAddresses().contains(remoteAddr)) {
      throw new UnrecoganizedRemoteIPAddressException(remoteAddr);
    }
    UUID sessionId = account.getSessionMap().get(applicationId);
    if (sessionId != null) {
      Optional<Session> sessionOptional = sessionRepository.findById(sessionId);
      if (sessionOptional.isPresent()) {
        return assignAuthTokenToUser(tenant, account, sessionOptional.get(), account.getName());
      }
    }
    DateTime expiry = DateTime.now();
    expiry.plusSeconds(new Long(SanjnanConstants.ONE_DAY).intValue());
    Session session
            = new Session(tenant.getId(), account, generateAuthToken(tenant, account.getName()), remoteAddr, applicationId, expiry, Session.SESSION_TYPE.APPLICATION_SESSION);
    account.getSessionMap().put(applicationId, session.getId());
    accountRepository.save(account);
    sessionRepository.save(session);
    return new H2OUsernameAndTokenResponse(tenant.getDiscriminator(),
            account.getName(),
            new H2OTokenResponse(session.getAuthToken(),
                    account.getComputeRegion(),
                    session.getExpiry(),
                    account.getH2ORoles()));
  }
  /**
   * This method generates an auth token for user and returns it. This method is called when a user performs authentication
   * using username and pasword. Usual validation including the validation of the ip address is also performed.
   *
   * @param username
   * @return
   * @throws DatatypeConfigurationException
   */
  @Transactional
  public H2OUsernameAndTokenResponse assignAuthTokenToUser(Tenant tenant,
                                                           Account account,
                                                           Session session,
                                                           String username)
          throws DatatypeConfigurationException {

    String authToken = generateAuthToken(tenant, account.getName());
    session.setAuthToken(authToken);
    sessionRepository.save(session);
    return new H2OUsernameAndTokenResponse(tenant.getDiscriminator(),
            username,
            new H2OTokenResponse(session.getAuthToken(),
                    account.getComputeRegion(),
                    session.getExpiry(),
                    account.getH2ORoles()));
  }
  public H2OUsernameAndTokenResponse deleteToken(Tenant tenant,
                                                 Account account,
                                                 Session session)
          throws DatatypeConfigurationException {

    account.getSessionMap().remove(session.getApplicationId());
    accountRepository.save(account);
    sessionRepository.delete(session);
    SanjnanAuthenticationThreadLocal.INSTANCE.clear();
    return new H2OUsernameAndTokenResponse(tenant.getDiscriminator(),
            account.getName(),
            new H2OTokenResponse("",
                    null,
                    new DateTime(),
                    account.getH2ORoles()));
  }

  private String generateAuthToken(Tenant tenant, String username) {
    UUID uuid1 = UUID.randomUUID();
    UUID uuid2 = UUID.randomUUID();

    String token = Long.toHexString(uuid1.getLeastSignificantBits()) +
            Long.toHexString(uuid1.getMostSignificantBits()) +
            Hex.encodeHexString(tenant.getName().getBytes()) +
            Long.toHexString(uuid2.getLeastSignificantBits()) +
            Long.toHexString(uuid2.getMostSignificantBits()) +
            Hex.encodeHexString(username.getBytes());
    return token;
  }

}
