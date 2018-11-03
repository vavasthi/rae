/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.identityserver.endpoints.v1;

import com.sanjnan.rae.common.constants.SanjnanConstants;
import com.sanjnan.rae.common.exception.MismatchedCredentialHeaderAndAuthException;
import com.sanjnan.rae.identityserver.data.couchbase.AccountRepository;
import com.sanjnan.rae.identityserver.data.couchbase.SessionRepository;
import com.sanjnan.rae.identityserver.data.couchbase.TenantRepository;
import com.sanjnan.rae.identityserver.pojos.*;
import com.sanjnan.rae.identityserver.security.filters.SanjnanAuthenticationThreadLocal;
import com.sanjnan.rae.identityserver.services.H2OTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import java.util.*;

/**
 * Created by vinay on 2/3/16.
 */
@RestController
public class AuthenticationEndpoint extends BaseEndpoint {

  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpServletResponse response;
  @Autowired
  private H2OTokenService tokenService;
  @Autowired
  private TenantRepository tenantRepository;
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private SessionRepository sessionRepository;

  @Transactional
  @RequestMapping(value = SanjnanConstants.V1_AUTHENTICATE_URL, method = RequestMethod.POST)
  public String authenticate(@PathVariable("tenant") String tenant) {
    return "This is just for in-code-documentation purposes and Rest API reference documentation." +
            "Servlet will never get to this point as Http requests are processed by AuthenticationFilter." +
            "Nonetheless to authenticate Domain User POST request with X-Auth-Username and X-Auth-Password headers " +
            "is mandatory to this URL. If username and password are correct valid token will be returned (just json string in response) " +
            "This token must be present in X-Auth-Token header in all requests for all other URLs, including logout." +
            "Authentication can be issued multiple times and each call results in new ticket.";
  }

  @Transactional
  @RequestMapping(value = SanjnanConstants.V1_AUTHENTICATE_URL + "/refresh", method = RequestMethod.POST)
  public H2OTokenResponse refresh(@PathVariable("tenant") String tenant) {

    HttpServletRequest httpRequest = asHttp(request);
    Optional<String> tenantHeader = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_TENANT_HEADER);
    Optional<String> token = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_TOKEN_HEADER);
    Optional<String> tokenTypeStr = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_TOKEN_TYPE_HEADER);
    Optional<String> remoteAddr = Optional.ofNullable(httpRequest.getRemoteAddr());
    Optional<String> applicationId = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_APPLICATION_ID_HEADER);
    try {

      H2OUsernameAndTokenResponse utResponse
              = tokenService.refresh(tenantHeader.get(), remoteAddr.get(), applicationId.get(), token.get());
      H2OTokenResponse tokenResponse
              = utResponse.getResponse();

      return tokenResponse;
    }
    catch(DatatypeConfigurationException ex) {
      throw new MismatchedCredentialHeaderAndAuthException("Datatype configuration error.");
    }
  }
  @Transactional
  @RequestMapping(value = SanjnanConstants.V1_AUTHENTICATE_URL + "/validate", method = RequestMethod.POST)
  public H2OTokenResponse validate(@PathVariable("tenant") String tenant) {

    HttpServletRequest httpRequest = asHttp(request);
    Optional<String> tenantHeader = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_TENANT_HEADER);
    Optional<String> token = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_TOKEN_HEADER);
    Optional<String> tokenTypeStr = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_TOKEN_TYPE_HEADER);
    Optional<String> remoteAddr = Optional.ofNullable(httpRequest.getRemoteAddr());
    Optional<String> applicationId = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_APPLICATION_ID_HEADER);
    try {

      H2OUsernameAndTokenResponse utResponse
              = tokenService.contains(tenantHeader.get(), remoteAddr.get(), applicationId.get(), token.get());
      H2OTokenResponse tokenResponse
              = utResponse.getResponse();

      return tokenResponse;
    }
    catch(DatatypeConfigurationException ex) {
      throw new MismatchedCredentialHeaderAndAuthException("Datatype configuration error.");
    }
  }

  @Transactional
  @RequestMapping(value = SanjnanConstants.V1_AUTHENTICATE_URL, method = RequestMethod.DELETE)
  public List<H2OTokenResponse> deleteToken(@PathVariable("tenant") String discriminator) {

    HttpServletRequest httpRequest = asHttp(request);
    Optional<String> tenantHeader = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_TENANT_HEADER);
    Optional<String> token = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_TOKEN_HEADER);
    Optional<String> tokenTypeStr = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_TOKEN_TYPE_HEADER);
    Optional<String> remoteAddr = Optional.ofNullable(httpRequest.getRemoteAddr());
    Optional<String> applicationId = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_APPLICATION_ID_HEADER);
    List<H2OTokenResponse> responses = new ArrayList<>();
    try {

      Account account = SanjnanAuthenticationThreadLocal.INSTANCE.getAccountThreadLocal().get();
      List<UUID> sessions = new ArrayList<>();
      account.getSessionMap().values().stream().forEach(e -> sessions.add(e));
      for (UUID sid : sessions) {

        Optional<Session> sessionOptional = sessionRepository.findById(sid);
        if (sessionOptional.isPresent()) {

          H2OUsernameAndTokenResponse utResponse = tokenService.deleteToken(SanjnanAuthenticationThreadLocal.INSTANCE.getTenantThreadLocal().get(),
                  SanjnanAuthenticationThreadLocal.INSTANCE.getAccountThreadLocal().get(),
                  sessionOptional.get());
          responses.add(utResponse.getResponse());
        }
      }
      return responses;
    }
    catch(DatatypeConfigurationException ex) {
      throw new MismatchedCredentialHeaderAndAuthException("Datatype configuration error.");
    }
  }

  @Transactional
  @RequestMapping(value = SanjnanConstants.V1_AUTHENTICATE_URL + "/{token}", method = RequestMethod.DELETE)
  public H2OTokenResponse deleteToken(@PathVariable("tenant") String tenant,
                                      @PathVariable("token") String token) {

    try {

      H2OUsernameAndTokenResponse utResponse = tokenService.deleteToken(SanjnanAuthenticationThreadLocal.INSTANCE.getTenantThreadLocal().get(),
              SanjnanAuthenticationThreadLocal.INSTANCE.getAccountThreadLocal().get(),
              SanjnanAuthenticationThreadLocal.INSTANCE.getSessionThreadLocal().get());
      H2OTokenResponse tokenResponse = utResponse.getResponse();
      return tokenResponse;
    }
    catch(DatatypeConfigurationException ex) {
      throw new MismatchedCredentialHeaderAndAuthException("Datatype configuration error.");
    }
  }
  private HttpServletRequest asHttp(ServletRequest request) {
    return (HttpServletRequest) request;
  }

  private HttpServletResponse asHttp(ServletResponse response) {
    return (HttpServletResponse) response;
  }

  private Optional<String>
  getOptionalHeader(HttpServletRequest httpRequest, String headerName) {
    return Optional.ofNullable(httpRequest.getHeader(headerName));
  }

}
