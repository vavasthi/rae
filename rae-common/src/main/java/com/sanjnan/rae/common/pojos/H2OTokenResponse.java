package com.sanjnan.rae.common.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sanjnan.rae.common.serializers.H2ODateTimeDeserializer;
import com.sanjnan.rae.common.serializers.H2ODateTimeSerializer;
import org.joda.time.DateTime;

import javax.xml.datatype.DatatypeConfigurationException;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by vinay on 2/3/16.
 */
public class H2OTokenResponse implements Serializable {

  @JsonProperty
  private String authToken;
  private String refreshToken;
  private ComputeRegion computeRegion;
  @JsonSerialize(using = H2ODateTimeSerializer.class)
  @JsonDeserialize(using = H2ODateTimeDeserializer.class)
  private DateTime expiry;
  private Collection<H2ORole> h2ORoles;

  public H2OTokenResponse() {
  }

  public H2OTokenResponse(String authToken,
                          String refreshToken,
                          ComputeRegion computeRegion,
                          DateTime expiry,
                          Collection<H2ORole> h2ORoles) throws DatatypeConfigurationException {
    this.authToken = authToken;
    this.refreshToken = refreshToken;
    this.computeRegion = computeRegion;
    this.h2ORoles = h2ORoles;
    this.expiry = expiry;
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

  public DateTime getExpiry() {
    return expiry;
  }

  public void setExpiry(DateTime expiry) {
    this.expiry = expiry;
  }

  public Collection<H2ORole> getH2ORoles() {
    return h2ORoles;
  }

  public void setH2ORoles(Collection<H2ORole> h2ORoles) {
    this.h2ORoles = h2ORoles;
  }

  public ComputeRegion getComputeRegion() {
    return computeRegion;
  }

  public void setComputeRegion(ComputeRegion computeRegion) {
    this.computeRegion = computeRegion;
  }
}