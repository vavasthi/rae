/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.identityserver.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanjnan.rae.common.constants.SanjnanConstants;
import com.sanjnan.rae.common.exception.MismatchedCredentialHeaderAndAuthException;
import com.sanjnan.rae.identityserver.pojos.H2OTokenResponse;
import com.sanjnan.rae.identityserver.pojos.H2OUsernameAndTokenResponse;
import com.sanjnan.rae.identityserver.pojos.Tenant;
import com.sanjnan.rae.identityserver.security.token.H2OPrincipal;
import com.sanjnan.rae.identityserver.security.token.H2OTokenPrincipal;
import com.sanjnan.rae.identityserver.services.AccountService;
import com.sanjnan.rae.identityserver.services.H2OTokenService;
import com.sanjnan.rae.identityserver.services.TenantService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by vinay on 2/3/16.
 */
public class H2OAuthenticationFilter extends GenericFilterBean {

  public static final String TOKEN_SESSION_KEY = "token";
  public static final String USER_SESSION_KEY = "user";
  private final static Logger logger = Logger.getLogger(H2OAuthenticationFilter.class);
  private AccountService accountService = null;
  private TenantService tenantService = null;
  private H2OTokenService tokenService = null;
  private AuthenticationManager authenticationManager;

  public H2OAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  /**
   * Entry point into the authentication filter. We check if the token and token cache is present, we do token based
   * authentication. Otherwise we assume it to be username and password based authentication.
   * @param request
   * @param response
   * @param chain
   * @throws IOException
   * @throws ServletException
   */
  @Override
  public void doFilter(ServletRequest request,
                       ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {

    HttpServletRequest httpRequest = asHttp(request);
    HttpServletResponse httpResponse = asHttp(response);

    Optional<String> username = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_USERNAME_HEADER);
    Optional<String> password = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_PASSWORD_HEADER);
    Optional<String> tenant = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_TENANT_HEADER);
    Optional<String> token = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_TOKEN_HEADER);
    Optional<String> basicAuth = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_AUTHORIZATION_HEADER);
    Optional<String> remoteAddr = Optional.ofNullable(httpRequest.getRemoteAddr());
    Optional<String> applicationId = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_APPLICATION_ID_HEADER);
    if(!applicationId.isPresent()) {

      throw new MismatchedCredentialHeaderAndAuthException("Application Id is not provided as header for authentication.");
    }
    if (basicAuth.isPresent()) {

      String basicAuthValue = new String(Base64.decode(basicAuth.get().substring("Basic ".length()).getBytes()));
      String[] basicAuthPair = basicAuthValue.split(":", 2);
      if (username.isPresent() && password.isPresent()) {
        if (basicAuth.isPresent() && basicAuth.get() != null && !basicAuth.get().isEmpty()) {

          validateCredentialFields(username, password, basicAuthPair);
        }
      }
      else {
        username = Optional.ofNullable(basicAuthPair[0]);
        password = Optional.ofNullable(basicAuthPair[1]);
      }

    }
    boolean tokenBasedAuthentication = true;
    if (token.isPresent() &&
            tenant.isPresent() ) {
      tokenBasedAuthentication = true;
    }
    else if (remoteAddr.isPresent() && tenant.isPresent() && username.isPresent() && password.isPresent()) {
      tokenBasedAuthentication = false;
    }
    else {
      throw new MismatchedCredentialHeaderAndAuthException("Insufficient headers for username/password as well token " +
              "based authentication");
    }
    String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);
    String[] resourcePathArray = resourcePath.split("/", -1); // Split the url as many times as required.
    if (resourcePathArray.length > 2) {

      String urlTenant = resourcePathArray[2];
      if (tenant.isPresent()) {

        if (!urlTenant.equals(tenant.get()) && !tenant.get().equals(SanjnanConstants.H2O_INTERNAL_TENANT)) {
          throw new MismatchedCredentialHeaderAndAuthException("Mismatched tenants in header and URL" + urlTenant + " and " + tenant);
        }
      }
      else {
        // The URL is not recognizable.
        throw new MismatchedCredentialHeaderAndAuthException(String.format("The URL is not in VERSION/NAME " + "format. {}", resourcePath));
      }
    }
    else {
      throw new MismatchedCredentialHeaderAndAuthException("Tenant header is absent.");
    }

    try {
      if (!tokenBasedAuthentication && postToAuthenticate(httpRequest, tenant.get(), resourcePath)) {
        logger.log(Level.INFO,
                String.format("Trying to authenticate user {} by X-Auth-Username method", username));
        processUsernamePasswordAuthentication(httpRequest,
                httpResponse,
                remoteAddr,
                applicationId,
                tenant,
                username,
                password);
        return;
      }

      if (tokenBasedAuthentication) {
        logger.log(Level.INFO,
                String.format("Trying to authenticate user by X-Auth-Token method. Token: {}", token));
        H2OUsernameAndTokenResponse utResponse
                = getTokenService(httpRequest).
                contains(tenant.get(), remoteAddr.get(), applicationId.get(), token.get());
        H2OTokenResponse tokenResponse
                = utResponse.getResponse();
        if (tokenResponse != null) {

          processTokenAuthentication(remoteAddr, applicationId, tenant, utResponse.getUsername(), token);
        }
      }

      logger.debug("AuthenticationFilter is passing request down the filter chain");
      chain.doFilter(request, response);
    } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
      SecurityContextHolder.clearContext();
      logger.error("Internal authentication service exception", internalAuthenticationServiceException);
      httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    } catch (AuthenticationException authenticationException) {
      SecurityContextHolder.clearContext();
      httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
    } catch (DatatypeConfigurationException e) {
      SecurityContextHolder.clearContext();
      httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    } finally {
    }
  }

  private HttpServletRequest asHttp(ServletRequest request) {
    return (HttpServletRequest) request;
  }

  private HttpServletResponse asHttp(ServletResponse response) {
    return (HttpServletResponse) response;
  }

  private boolean postToAuthenticate(HttpServletRequest httpRequest, String tenant, String resourcePath) {
    String endPointURL = SanjnanConstants.V1_AUTHENTICATE_URL.replace(SanjnanConstants.TENANT_PARAMETER_PATTERN, tenant);
    return endPointURL.equalsIgnoreCase(resourcePath) && httpRequest.getMethod().equals("POST");
  }

  /**
   * This method gets called whent the user has tried to authenticate and has provided username and password
   * either in the form of X-Auth-Username and X-Auth-Password header or as a Basic Auth string. This method
   * creates a new token for the user and returns back to him.
   *
   * @param httpResponse http response
   * @param remoteAddr address of client
   * @param tenantDiscriminator tenantDiscriminator for which the authentication is being done
   * @param username username for which the authentication is being done
   * @param password password that is being used for authentication.
   * @throws IOException
   */
  private void processUsernamePasswordAuthentication(HttpServletRequest request,
                                                     HttpServletResponse httpResponse,
                                                     Optional<String> remoteAddr,
                                                     Optional<String> applicationId,
                                                     Optional<String> tenantDiscriminator,
                                                     Optional<String> username,
                                                     Optional<String> password) throws IOException, DatatypeConfigurationException {

    Optional<Tenant> tenantOptional = getTenantService(request).getTenant(tenantDiscriminator.get());
    if (tenantOptional.isPresent()) {

      Tenant tenant =  tenantOptional.get();
      Authentication resultOfAuthentication
              = tryToAuthenticateWithUsernameAndPassword(remoteAddr, applicationId, tenantDiscriminator, username, password);
      SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
      httpResponse.setStatus(HttpServletResponse.SC_OK);
      H2OTokenResponse tokenResponse
              = getTokenService(request).create(tenant.getDiscriminator(),
              remoteAddr.get(),
              applicationId.get(),
              username.get(),
              password.get()).getResponse();
      String tokenJsonResponse = new ObjectMapper().writeValueAsString(tokenResponse);
      httpResponse.addHeader("Content-Type", "application/json");
      httpResponse.getWriter().print(tokenJsonResponse);
    }
  }

  private Authentication tryToAuthenticateWithUsernameAndPassword(Optional<String> remoteAddr,
                                                                  Optional<String> applicationId,
                                                                  Optional<String> tenant,
                                                                  Optional<String> username,
                                                                  Optional<String> password) {
    UsernamePasswordAuthenticationToken requestAuthentication
            = new UsernamePasswordAuthenticationToken(new H2OPrincipal(remoteAddr, applicationId, tenant, username), password);
    return tryToAuthenticate(requestAuthentication);
  }

  private void processTokenAuthentication(Optional<String> remoteAddr,
                                          Optional<String> applicationId,
                                          Optional<String> tenant,
                                          String username,
                                          Optional<String> token) {

    Authentication resultOfAuthentication = tryToAuthenticateWithToken(remoteAddr,
            applicationId,
            tenant,
            username,
            token);
    SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
  }

  private Authentication tryToAuthenticateWithToken(Optional<String> remoteAddr,
                                                    Optional<String> applicationId,
                                                    Optional<String> tenant,
                                                    String username,
                                                    Optional<String> token) {
    PreAuthenticatedAuthenticationToken requestAuthentication
            = new PreAuthenticatedAuthenticationToken(new H2OTokenPrincipal(remoteAddr,
            applicationId,
            tenant,
            username,
            token), null);
    return tryToAuthenticate(requestAuthentication);
  }

  private Authentication tryToAuthenticate(Authentication requestAuthentication) {
    Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
    if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
      throw new InternalAuthenticationServiceException("Unable to authenticate Domain User for provided credentials");
    }
    logger.debug("User successfully authenticated");
    return responseAuthentication;
  }
  private void validateCredentialFields(Optional<String> field1, Optional<String> field2, String[] basicAuthPair)
          throws MismatchedCredentialHeaderAndAuthException {

    validateCredentialField(field1, basicAuthPair[0]);
    validateCredentialField(field2, basicAuthPair[1]);
  }
  private void validateCredentialField(Optional<String> field, String value)
          throws MismatchedCredentialHeaderAndAuthException {

    if (!field.isPresent()) {
      field = Optional.ofNullable(value);
    }
    else {
      if (!field.get().equals(value)) {

        logger.info(" USERNAME" + field.get() + " VALUE " + value);
        throw new MismatchedCredentialHeaderAndAuthException("Mismatched credential value and header");
      }
    }
  }
  private AccountService getAccountService(HttpServletRequest request) {

    synchronized (this) {

      if (accountService == null) {

        ServletContext servletContext = request.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        accountService =  webApplicationContext.getBean(AccountService.class);
      }
    }
    return accountService;
  }

  private TenantService getTenantService(HttpServletRequest request) {

    synchronized (this) {

      if (tenantService == null) {

        ServletContext servletContext = request.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        tenantService =  webApplicationContext.getBean(TenantService.class);
      }
    }
    return tenantService;
  }
  private H2OTokenService getTokenService(HttpServletRequest request) {

    synchronized (this) {

      if (tokenService == null) {

        ServletContext servletContext = request.getServletContext();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        tokenService =  webApplicationContext.getBean(H2OTokenService.class);
      }
    }
    return tokenService;
  }
  private Optional<String>
  getOptionalHeader(HttpServletRequest httpRequest, String headerName) {
    return Optional.ofNullable(httpRequest.getHeader(headerName));
  }
}