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
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
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
                                              String authToken,
                                              boolean refreshTokenExpected)
          throws DatatypeConfigurationException {

    return validateAppToken(tenantDiscriminator, remoteAddr, applicationId, authToken, refreshTokenExpected);
  }
  public Session create(String tenantDiscriminator,
                        String remoteAddr,
                        String clientIdId,
                        String username,
                        String password) {

    Optional<Tenant> tenantOptional = tenantRepository.findByDiscriminator(tenantDiscriminator);
    try {

      if (tenantOptional.isPresent()) {

        Tenant tenant = tenantOptional.get();
        Optional<Account> accountOptional = accountRepository.findAccountsByTenantIdAndName(tenant.getId(), username);
        if (accountOptional.isPresent()) {

          Account account = accountOptional.get();
          if (H2OPasswordEncryptionManager.INSTANCE.matches(password, account.getPassword())) {

            String sessionId = account.getSessionMap().get(clientIdId);
            if (sessionId != null) {
              Optional<Session> sessionOptional = sessionRepository.findById(sessionId);
              if (sessionOptional.isPresent()) {
                Session session = sessionOptional.get();
                deleteToken(tenant, account, session);
                accountOptional = accountRepository.findById(account.getId());
                account = accountOptional.get();
              }
            }
            Session session = assignAuthTokenToUser(tenant, account, remoteAddr, clientIdId);
            SanjnanAuthenticationThreadLocal.INSTANCE.initializeThreadLocals(tenant, account, session);
            account.getRemoteAddresses().add(remoteAddr);
            accountRepository.save(account);
            return session;
          }
          else {
            throw new BadCredentialsException(String.format("%s is not authorized. Credentials mismatch", username));
          }
        }
      }
    }
    catch(DatatypeConfigurationException dce) {

      throw new BadCredentialsException(remoteAddr, dce);
    }
    throw new BadCredentialsException(remoteAddr);
  }

  /**
   * This method is called when a token needs to be refreshed. The request is validated against the token that
   * is sent as part of the request, a new token is generated and returned.
   *
   *
   * @param remoteAddr the ip address from which the incoming request came
   * @return token response object.
   * @throws DatatypeConfigurationException
   */
  public H2OUsernameAndTokenResponse refresh(Tenant tenant,
                                             Account account,
                                             Session session,
                                             String remoteAddr)
          throws DatatypeConfigurationException {

    return refreshAppToken(tenant, account, session, remoteAddr);
  }

  private H2OUsernameAndTokenResponse validateAppToken(String tenantDiscriminator,
                                                       String remoteAddr,
                                                       String applicationId,
                                                       String authToken,
                                                       boolean refreshTokenExpected) throws DatatypeConfigurationException {

    Optional<Tenant> tenantOptional = tenantRepository.findByDiscriminator(tenantDiscriminator);
    if (tenantOptional.isPresent()) {

      Tenant tenant = tenantOptional.get();
      Optional<Session> sessionOptional;
      if (refreshTokenExpected) {
        sessionOptional = sessionRepository.findByRefreshToken(authToken);
      }
      else {
        sessionOptional = sessionRepository.findByAuthToken(authToken);
      }
      if (sessionOptional.isPresent()) {

        Session session = sessionOptional.get();
        Optional<Account> accountOptional = accountRepository.findById(session.getAccountId());
        if (accountOptional.isPresent()) {

          Account account = accountOptional.get();

          if (session.getClientId().equals(applicationId)) {
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
                            "",
                            account.getComputeRegion(),
                            session.getExpiry(),
                            account.getH2ORoles()));
          }
        }
      } else {

        throw new BadCredentialsException(String.format("Token %s doesn't belong to application %s", authToken, applicationId));
      }
    }
    throw new BadCredentialsException(tenantDiscriminator + "/" + remoteAddr);
  }

  private H2OUsernameAndTokenResponse refreshAppToken(Tenant tenant,
                                                      Account account,
                                                      Session session,
                                                      String remoteAddr)
          throws DatatypeConfigurationException {

          if (!account.getRemoteAddresses().contains(remoteAddr)) {
            throw new UnrecoganizedRemoteIPAddressException(remoteAddr);
          }
          H2OUsernameAndTokenResponse response =  assignAuthTokenToUser(tenant, account, session, account.getName());
          SanjnanAuthenticationThreadLocal.INSTANCE.initializeThreadLocals(tenant, account, session);
          return response;
  }
  public Session assignAuthTokenToUser(Tenant tenant,
                                       Account account,
                                       String remoteAddr,
                                       String applicationId)
          throws DatatypeConfigurationException {
    String sessionId = account.getSessionMap().get(applicationId);
    if (sessionId != null) {
      Optional<Session> sessionOptional = sessionRepository.findById(sessionId);
      if (sessionOptional.isPresent()) {
        assignAuthTokenToUser(tenant, account, sessionOptional.get(), account.getName());
        return sessionOptional.get();
      }
    }
    DateTime expiry = DateTime.now();
    expiry = expiry.plusSeconds(SanjnanConstants.TOKEN_EXPIRY_SECONDS);
    Session session
            = new Session(tenant.getId().toString(), account, generateAuthToken(tenant, account.getName()), generateAuthToken(tenant, account.getName()), remoteAddr, applicationId, expiry, Session.SESSION_TYPE.APPLICATION_SESSION);
    account.getSessionMap().put(applicationId, session.getId().toString());
    accountRepository.save(account);
    sessionRepository.save(session);
    return session;
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
    authToken = generateAuthToken(tenant, account.getName());
    session.setRefreshToken(authToken);
    sessionRepository.save(session);
    return new H2OUsernameAndTokenResponse(tenant.getDiscriminator(),
            username,
            new H2OTokenResponse(session.getAuthToken(),
                    session.getRefreshToken(),
                    account.getComputeRegion(),
                    session.getExpiry(),
                    account.getH2ORoles()));
  }
  public H2OUsernameAndTokenResponse deleteToken(Tenant tenant,
                                                 Account account,
                                                 Session session)
          throws DatatypeConfigurationException {

    account.getSessionMap().remove(session.getClientId());
    accountRepository.save(account);
    sessionRepository.delete(session);
    SanjnanAuthenticationThreadLocal.INSTANCE.clear();
    return new H2OUsernameAndTokenResponse(tenant.getDiscriminator(),
            account.getName(),
            new H2OTokenResponse("",
                    "",
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
