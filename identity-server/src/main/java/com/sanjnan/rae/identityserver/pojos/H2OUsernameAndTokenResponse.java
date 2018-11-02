/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.identityserver.pojos;

import java.io.Serializable;

/**
 * Created by vinay on 2/11/16.
 */
public class H2OUsernameAndTokenResponse implements Serializable {
  public H2OUsernameAndTokenResponse(String tenant, String username, H2OTokenResponse response) {
    this.username = username;
    this.tenant = tenant;
    this.response = response;
  }

  public H2OUsernameAndTokenResponse() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getTenant() {
    return tenant;
  }

  public void setTenant(String tenant) {
    this.tenant = tenant;
  }

  public H2OTokenResponse getResponse() {
    return response;
  }

  public void setResponse(H2OTokenResponse response) {
    this.response = response;
  }

  private String username;
  private String tenant;
  private H2OTokenResponse response;

}
