/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.oauth2client.security.filters;

import com.couchbase.client.deps.com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sanjnan.rae.common.constants.SanjnanConstants;
import com.sanjnan.rae.common.exception.*;
import com.sanjnan.rae.common.security.token.SanjnanOAuthTokenPrincipal;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
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

    HttpServletResponse httpResponse = asHttp(response);
    try {
    HttpServletRequest httpRequest = asHttp(request);

    Optional<String> token = getOptionalParameter(httpRequest,"token");
    Optional<String> tokenType = getOptionalParameter(httpRequest,"token");
    Optional<String> authorizationHeader = Optional.of(httpRequest.getHeader("Authorization"));
    SanjnanOAuthTokenPrincipal authTokenPrincipal = new SanjnanOAuthTokenPrincipal(token, tokenType, authorizationHeader);
    processTokenAuthentication(authTokenPrincipal);
    chain.doFilter(request, response);
    } catch (InternalAuthenticationServiceException e) {
      SecurityContextHolder.clearContext();
      handleExceptions(e, asHttp(response));
      logger.error("Internal authentication service exception", e);
    } catch (AuthenticationException e) {
      SecurityContextHolder.clearContext();
      handleExceptions(e, asHttp(response));
    } catch(Exception e) {

      handleExceptions(e, asHttp(response));
    }
    finally {
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
  private void handleExceptions(Exception ex, HttpServletResponse response) {
    try {

      if (ex instanceof EntityAlreadyExistsException) {
        EntityAlreadyExistsException e = (EntityAlreadyExistsException) ex;
        handleExceptionMsg(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getErrorCode(), response, e);
      } else if (ex instanceof NotFoundException) {
        NotFoundException e = (NotFoundException) ex;
        handleExceptionMsg(HttpStatus.NOT_FOUND.value(), e.getErrorCode(), response, e);
      } else {
        handleExceptionMsg(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.value(), response, ex);
      }
    }
    catch(Exception jsonex) {
      logger.log(Level.ERROR, "Error during error generation", jsonex);
    }
  }

  private void handleExceptionMsg(int status, int errorCode, HttpServletResponse response, Exception e)
          throws IOException {
    ExceptionResponse er
            = new ExceptionResponseBuilder()
            .setStatus(status)
            .setCode(errorCode)
            .setMessage(e.getMessage())
            .setMoreInfo(String.format(SanjnanConstants.EXCEPTION_URL,errorCode))
            .createExceptionResponse();
    response.setStatus(status);
    Gson gson = new Gson();
    String tokenJsonResponse = gson.toJson(er);
    response.addHeader("Content-Type", "application/json");
    response.getWriter().print(tokenJsonResponse);

    if (e instanceof BadRequestException ||
            e instanceof BadCredentialsException ||
            e instanceof BadClientCredentialsException ) {
      // these are repetitive exceptions and having stacktrace in the logs does not help us..
      // rather log becomes too big.
      // Log only the message so we can keep track
      logger.error(e.getMessage());
    } else {
      // log the entire details.. we need the stack trace here...
      logger.error(tokenJsonResponse, e);
    }
  }

  private Optional<String> getOptionalParameter(HttpServletRequest httpRequest, String parameterName) {
    String[] values = httpRequest.getParameterValues(parameterName);
    if (values.length == 0) {
      return Optional.of((String)null);
    }
    return Optional.of(values[0]);
  }
}