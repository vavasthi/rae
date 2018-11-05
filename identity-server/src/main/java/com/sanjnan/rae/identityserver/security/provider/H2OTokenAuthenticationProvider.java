package com.sanjnan.rae.identityserver.security.provider;

import com.sanjnan.rae.common.enums.Role;
import com.sanjnan.rae.identityserver.pojos.H2ORole;
import com.sanjnan.rae.identityserver.pojos.H2OTokenResponse;
import com.sanjnan.rae.identityserver.security.token.H2OTokenPrincipal;
import com.sanjnan.rae.identityserver.services.H2OTokenService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vinay on 2/3/16.
 */
public class H2OTokenAuthenticationProvider implements AuthenticationProvider {

  private final static Logger logger = Logger.getLogger(H2OTokenAuthenticationProvider.class);

  @Autowired
  private H2OTokenService tokenService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    H2OTokenPrincipal principal = (H2OTokenPrincipal)authentication.getPrincipal();
    if (!principal.isValid()) {
      throw new BadCredentialsException("Invalid token");
    }
    if (principal.isRefreshTokenExpected()) {
      return processForRefreshToken(principal);
    }
    else {
      return processForAuthToken(principal);
    }
  }
  private Authentication processForAuthToken(H2OTokenPrincipal principal) {

    logger.info("Called authenticated " + principal.toString());
    H2OTokenResponse response = null;
    try {
      response = tokenService.contains(principal.getTenant().get(),
              principal.getRemoteAddr().get(),
              principal.getApplicationId().get(),
              principal.getToken().get(),
              false).getResponse();

    } catch (DatatypeConfigurationException e) {
      logger.log(Level.ERROR, "XMLGregorian Calendar conversion error.", e);
    }
    if (response != null) {
      List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
      if (response.getH2ORoles() != null) {

        response.getH2ORoles().forEach(e -> grantedAuthorityList.add(e));
      }
      return new PreAuthenticatedAuthenticationToken(principal, response.getAuthToken(), grantedAuthorityList);
    }
    throw new BadCredentialsException("Invalid token or token expired");
  }
  private Authentication processForRefreshToken(H2OTokenPrincipal principal) {

    logger.info("Called authenticated for refresh token" + principal.toString());
    H2OTokenResponse response = null;
    try {
      response = tokenService.contains(principal.getTenant().get(),
              principal.getRemoteAddr().get(),
              principal.getApplicationId().get(),
              principal.getToken().get(),
              true).getResponse();

    } catch (DatatypeConfigurationException e) {
      logger.log(Level.ERROR, "XMLGregorian Calendar conversion error.", e);
    }
    if (response != null) {
      List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
      grantedAuthorityList.add(new H2ORole(Role.REFRESH.name()));
      return new PreAuthenticatedAuthenticationToken(principal, response.getAuthToken(), grantedAuthorityList);
    }
    throw new BadCredentialsException("Invalid token or token expired");
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(PreAuthenticatedAuthenticationToken.class);
  }
}
