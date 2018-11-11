/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.oauth2client.security.filters;

import com.sanjnan.rae.common.security.token.SanjnanOAuthTokenPrincipal;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by vinay on 2/3/16.
 */
public class SanjnanAuthenticationFilter extends GenericFilterBean {

  public static final String TOKEN_SESSION_KEY = "token";
  public static final String USER_SESSION_KEY = "user";
  private final static Logger logger = Logger.getLogger(SanjnanAuthenticationFilter.class);
  private AuthenticationManager authenticationManager;

  public SanjnanAuthenticationFilter(AuthenticationManager authenticationManager) {
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

    try {
    HttpServletRequest httpRequest = asHttp(request);
    HttpServletResponse httpResponse = asHttp(response);

    Optional<String> token = getOptionalParameter(httpRequest,"token");
    Optional<String> tokenType = getOptionalParameter(httpRequest,"token");
    Optional<String> authorizationHeader = Optional.of(httpRequest.getHeader("Authorization"));
    SanjnanOAuthTokenPrincipal authTokenPrincipal = new SanjnanOAuthTokenPrincipal(token, tokenType, authorizationHeader);
    processTokenAuthentication(authTokenPrincipal);
    chain.doFilter(request, response);
    } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
      SecurityContextHolder.clearContext();
      logger.error("Internal authentication service exception", internalAuthenticationServiceException);
    } catch (AuthenticationException authenticationException) {
      SecurityContextHolder.clearContext();
    } finally {
    }
  }

  private HttpServletRequest asHttp(ServletRequest request) {
    return (HttpServletRequest) request;
  }

  private HttpServletResponse asHttp(ServletResponse response) {
    return (HttpServletResponse) response;
  }

  private void processTokenAuthentication(SanjnanOAuthTokenPrincipal tokenPrincipal) {

    Authentication resultOfAuthentication = tryToAuthenticateWithToken(tokenPrincipal);
    SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
  }

  private Authentication tryToAuthenticateWithToken(SanjnanOAuthTokenPrincipal tokenPrincipal) {
    PreAuthenticatedAuthenticationToken requestAuthentication
        = new PreAuthenticatedAuthenticationToken(tokenPrincipal, null);
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
  private Optional<String> getOptionalParameter(HttpServletRequest httpRequest, String parameterName) {
    String[] values = httpRequest.getParameterValues(parameterName);
    if (values.length == 0) {
      return Optional.of((String)null);
    }
    return Optional.of(values[0]);
  }
}