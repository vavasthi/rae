/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sanjnan.rae.common.serializers.H2ODateTimeDeserializer;
import com.sanjnan.rae.common.serializers.H2ODateTimeSerializer;
import org.joda.time.DateTime;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.UUID;

/**
 * Created by vinay on 1/28/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
public class Session extends Base {
  public enum SESSION_TYPE {
    APPLICATION_SESSION(1, "application"),
    DEVICE_SESSION(2, "device"),
    WEBAPP_SESSION(3, "webapp"),
    REST_SESSION(4, "rest"),
    UNKNOWN_SESSION(0, "unknown");

    private final int ivalue;
    private final String svalue;

    SESSION_TYPE(Integer ivalue, String svalue) {
      this.ivalue = ivalue;
      this.svalue = svalue;
    }

    public static SESSION_TYPE createFromString(String value) {
      for (SESSION_TYPE t : values()) {
        if (t.svalue.equalsIgnoreCase(value)) {
          return t;
        }
      }
      return UNKNOWN_SESSION;
    }
    public static SESSION_TYPE createFromInteger(int value) {
      for (SESSION_TYPE t : values()) {
        if (t.ivalue == value) {
          return t;
        }
      }
      return UNKNOWN_SESSION;
    }
    public int getIValue() {
      return ivalue;
    }
    public String getSValue() {
      return svalue;
    }
    @Override
    public String toString() {
      return svalue;
    }

  }

  @JsonSerialize(using = H2ODateTimeSerializer.class)
  @JsonDeserialize(using = H2ODateTimeDeserializer.class)
  private DateTime expiry;
  private SESSION_TYPE sessionType;

  public Session(Account account,
                 String authToken,
                 String refreshToken,
                 String remoteAddress,
                 String clientId,
                 DateTime expiry,
                 SESSION_TYPE sessionType) {
    super(UUID.randomUUID().toString(), DateTime.now(), DateTime.now(), account.getId(), account.getId(), account.getName());

    this.accountId = account.getId();
    this.authToken = authToken;
    this.refreshToken = refreshToken;
    this.remoteAddress = remoteAddress;
    this.clientId = clientId;
    this.expiry = expiry;
    this.sessionType = sessionType;
  }

  public Session() {

  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public String getRemoteAddress() {
    return remoteAddress;
  }

  public void setRemoteAddress(String remoteAddress) {
    this.remoteAddress = remoteAddress;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public DateTime getExpiry() {
    return expiry;
  }

  public void setExpiry(final DateTime expiry) {
    this.expiry = expiry;
  }

  public SESSION_TYPE getSessionType() {
    return sessionType;
  }

  public void setSessionType(final SESSION_TYPE sessionType) {
    this.sessionType = sessionType;
  }

  private String authToken;
  private String refreshToken;
  private String remoteAddress;
  private String clientId;
  private String accountId;
}
