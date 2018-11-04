package com.sanjnan.rae.identityserver.pojos;

import com.couchbase.client.java.repository.annotation.Field;
import com.sanjnan.rae.common.annotations.SkipPatching;
import org.joda.time.DateTime;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.*;

/**
 * Created by vinay on 1/28/16.
 */
@Document
public class Account extends Base {
  public Account() {
    super(UUID.randomUUID().toString(), DateTime.now(), DateTime.now(),null, null, "");
    setCreatedBy(getId());
    setCreatedBy(getId());
  }

  public String getTenantId() {
    return tenantId;
  }

  public void setTenantId(String tenantId) {
    this.tenantId = tenantId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @SkipPatching
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<H2ORole> getH2ORoles() {
    return h2ORoles;
  }

  public void setH2ORoles(Set<H2ORole> h2ORoles) {
    this.h2ORoles = h2ORoles;
  }

  public Set<String> getRemoteAddresses() {
    return remoteAddresses;
  }

  public void setRemoteAddresses(Set<String> remoteAddresses) {
    this.remoteAddresses = remoteAddresses;
  }

  public Map<String, String> getSessionMap() {
    return sessionMap;
  }

  public void setSessionMap(Map<String, String> sessionMap) {
    this.sessionMap = sessionMap;
  }

  public ComputeRegion getComputeRegion() {
    return computeRegion;
  }

  public void setComputeRegion(ComputeRegion computeRegion) {
    this.computeRegion = computeRegion;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Account account = (Account) o;
    return Objects.equals(tenantId, account.tenantId) &&
            Objects.equals(email, account.email) &&
            Objects.equals(password, account.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), tenantId, email, password);
  }

  @Override
  public String toString() {
    return "Account{" +
        "tenant=" + tenantId +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", h2ORoles=" + h2ORoles +
        ", remoteAddresses=" + remoteAddresses +
        ", sessionMap=" + sessionMap +
        ", computeRegion=" + computeRegion +
        "} " + super.toString();
  }

  @Field()
  private String tenantId;
  private String email;
  private String password;
  private Set<H2ORole> h2ORoles = new HashSet<>();
  private Set<String> remoteAddresses = new HashSet<>();
  private Map<String, String> sessionMap = new HashMap<>();
  private ComputeRegion computeRegion;
}
