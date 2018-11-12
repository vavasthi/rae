package com.sanjnan.rae.common.security.token;



import com.sanjnan.rae.common.pojos.H2ORole;
import org.joda.time.DateTime;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Created by vinay on 2/3/16.
 */
public class SanjnanOAuthTokenPrincipal implements Principal {


  public SanjnanOAuthTokenPrincipal(Optional<String> token, Optional<String> tokenType, Optional<String> authorizationHeader) {
    this.name = (token.isPresent() ? token.get() : "None");
    this.token = token;
    this.tokenType = tokenType;
    this.authorizationHeader = authorizationHeader;
  }

  public Optional<String> getToken() {
    return token;
  }

  public void setToken(Optional<String> token) {
    this.token = token;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  public Optional<String> getTokenType() {
    return tokenType;
  }

  public void setTokenType(Optional<String> tokenType) {
    this.tokenType = tokenType;
  }

  public Optional<String> getAuthorizationHeader() {
    return authorizationHeader;
  }

  public void setAuthorizationHeader(Optional<String> authorizationHeader) {
    this.authorizationHeader = authorizationHeader;
  }

  private String name;
  private Optional<String> token;
  private Optional<String> tokenType;
  private Optional<String> authorizationHeader;
}
