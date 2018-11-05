package com.sanjnan.rae.identityserver.security.token;



import java.security.Principal;
import java.util.Optional;

/**
 * Created by vinay on 2/3/16.
 */
public class H2OTokenPrincipal extends H2OPrincipal {


  public H2OTokenPrincipal(Optional<String> remoteAddr,
                           Optional<String> applicationId,
                           Optional<String> tenant,
                           String name,
                           Optional<String> token,
                           boolean refreshTokenExpected) {
    super(remoteAddr, applicationId, tenant, Optional.ofNullable(name));
    this.token = token;
    this.refreshTokenExpected = refreshTokenExpected;
  }

  public Optional<String> getToken() {
    return token;
  }

  public void setToken(Optional<String> token) {
    this.token = token;
  }


  public boolean isValid() {
    return super.isValid() && validField(token);
  }

  public boolean isRefreshTokenExpected() {
    return refreshTokenExpected;
  }

  public void setRefreshTokenExpected(boolean refreshTokenExpected) {
    this.refreshTokenExpected = refreshTokenExpected;
  }

  @Override
  public String toString() {
    return "H2OTokenPrincipal{" +
        "token=" + token +
        "} " + super.toString();
  }

  private Optional<String> token;
  private boolean refreshTokenExpected;
}
