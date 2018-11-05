
package com.sanjnan.rae.identityserver.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanjnan.rae.common.constants.SanjnanConstants;
import com.sanjnan.rae.common.exception.*;
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
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.Base64;
import java.util.Optional;

/**
 * Created by vinay on 2/3/16.
 */
public class H2OAuthenticationFilter extends GenericFilterBean {

  enum AUTHENTICATION_TYPE {
    TOKEN,
    REFRESH,
    USERNAME_PASSWORD
  }
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
    Optional<String> clientId = getOptionalHeader(httpRequest, SanjnanConstants.AUTH_CLIENT_ID_HEADER);
    String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);
    String[] resourcePathArray = resourcePath.split("/", -1); // Split the url as many times as required.
    if(!clientId.isPresent()) {

      throw new MismatchedCredentialHeaderAndAuthException("Application Id is not provided as header for authentication.");
    }
    if (basicAuth.isPresent()) {

      String basicAuthValue = new String(Base64.getDecoder().decode(basicAuth.get().substring("Basic ".length()).getBytes()));
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
    boolean refreshTokenExpected = false;
    if (tenant.isPresent() && String.format(SanjnanConstants.REFRESH_TOKEN_URL, tenant.get()).equals(resourcePath)) {
      refreshTokenExpected = true;
    }
    AUTHENTICATION_TYPE authenticationType = AUTHENTICATION_TYPE.TOKEN;
    if (!refreshTokenExpected && token.isPresent() && tenant.isPresent() ) {
      authenticationType = AUTHENTICATION_TYPE.TOKEN;
    }
    else if (refreshTokenExpected && token.isPresent() && tenant.isPresent()) {
      authenticationType = AUTHENTICATION_TYPE.REFRESH;
    }
    else if (remoteAddr.isPresent() && tenant.isPresent() && username.isPresent() && password.isPresent()) {
      authenticationType = AUTHENTICATION_TYPE.USERNAME_PASSWORD;
    }
    else {
      throw new MismatchedCredentialHeaderAndAuthException("Insufficient headers for username/password as well token " +
              "based authentication");
    }
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
      if (authenticationType.equals(AUTHENTICATION_TYPE.USERNAME_PASSWORD) && postToAuthenticate(httpRequest, tenant.get(), resourcePath)) {
        logger.log(Level.INFO,
                String.format("Trying to authenticate user {%s} by X-Auth-Username method", username));
        processUsernamePasswordAuthentication(httpRequest,
                httpResponse,
                remoteAddr,
                clientId,
                tenant,
                username,
                password);
      }
      else if (authenticationType.equals(AUTHENTICATION_TYPE.TOKEN)) {
        logger.log(Level.INFO,
                String.format("Trying to authenticate user by X-Auth-Token method. Token: {%s}", token));
          processTokenAuthentication(remoteAddr, clientId, tenant, token);
      }
      else if (authenticationType.equals(AUTHENTICATION_TYPE.REFRESH)) {

        logger.log(Level.INFO,
                String.format("Trying to authenticate user by refresh X-Auth-Token method. Token: {%s}", token));
        processRefreshAuthentication(remoteAddr, clientId, tenant, token);
      }

      logger.debug("AuthenticationFilter is passing request down the filter chain");
      chain.doFilter(request, response);
    } catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
      SecurityContextHolder.clearContext();
      logger.error("Internal authentication service exception", internalAuthenticationServiceException);
      httpResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      handleExceptions(internalAuthenticationServiceException, httpResponse);
    } catch (AuthenticationException authenticationException) {
      SecurityContextHolder.clearContext();
      httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
      handleExceptions(authenticationException, httpResponse);
    } catch (DatatypeConfigurationException e) {
      SecurityContextHolder.clearContext();
      httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
      handleExceptions(e, httpResponse);
    } catch(Exception e) {
      handleExceptions(e, httpResponse);
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
                                                     Optional<String> clientId,
                                                     Optional<String> tenantDiscriminator,
                                                     Optional<String> username,
                                                     Optional<String> password) throws IOException, DatatypeConfigurationException {

    Authentication resultOfAuthentication
              = tryToAuthenticateWithUsernameAndPassword(remoteAddr, clientId, tenantDiscriminator, username, password);
      SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);

  }

  private Authentication tryToAuthenticateWithUsernameAndPassword(Optional<String> remoteAddr,
                                                                  Optional<String> clientId,
                                                                  Optional<String> tenant,
                                                                  Optional<String> username,
                                                                  Optional<String> password) {
    UsernamePasswordAuthenticationToken requestAuthentication
            = new UsernamePasswordAuthenticationToken(new H2OPrincipal(remoteAddr, clientId, tenant, username), password);
    return tryToAuthenticate(requestAuthentication);
  }

  private void processTokenAuthentication(Optional<String> remoteAddr,
                                          Optional<String> clientId,
                                          Optional<String> tenant,
                                          Optional<String> token) {

    Authentication resultOfAuthentication = tryToAuthenticateWithToken(remoteAddr,
            clientId,
            tenant,
            token,
            false);
    SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
  }

  private void processRefreshAuthentication(Optional<String> remoteAddr,
                                           Optional<String> clientId,
                                           Optional<String> tenant,
                                           Optional<String> token) {

    Authentication resultOfAuthentication = tryToAuthenticateWithToken(remoteAddr,
            clientId,
            tenant,
            token,
            true);
    SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
  }

  private Authentication tryToAuthenticateWithToken(Optional<String> remoteAddr,
                                                    Optional<String> clientId,
                                                    Optional<String> tenant,
                                                    Optional<String> token,
                                                    boolean refreshTokenExpected) {
    PreAuthenticatedAuthenticationToken requestAuthentication
            = new PreAuthenticatedAuthenticationToken(new H2OTokenPrincipal(remoteAddr,
            clientId,
            tenant,
            token.get(),
            token,
            refreshTokenExpected), null);
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
    String tokenJsonResponse;
    ExceptionResponse er
            = new ExceptionResponseBuilder()
            .setStatus(status)
            .setCode(errorCode)
            .setMessage(e.getMessage())
            .setMoreInfo(String.format(SanjnanConstants.EXCEPTION_URL,errorCode))
            .createExceptionResponse();
    response.setStatus(status);
    tokenJsonResponse = new ObjectMapper().writeValueAsString(er);
    response.addHeader("Content-Type", "application/json");
    response.getWriter().print(tokenJsonResponse);

    if (e instanceof BadRequestException ||
            e instanceof BadCredentialsException ) {
      // these are repetitive exceptions and having stacktrace in the logs does not help us..
      // rather log becomes too big.
      // Log only the message so we can keep track
      logger.error(e.getMessage());
    } else {
      // log the entire details.. we need the stack trace here...
      logger.error(tokenJsonResponse, e);
    }
  }

  private Optional<String>
  getOptionalHeader(HttpServletRequest httpRequest, String headerName) {
    return Optional.ofNullable(httpRequest.getHeader(headerName));
  }
}