package com.sanjnan.rae.common.pojos;

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

  public Account(DateTime createdAt,
                 DateTime updatedAt,
                 String name,
                 String email,
                 String password,
                 boolean expired,
                 boolean locked,
                 boolean credentialsExpired,
                 boolean enabled,
                 Set<H2ORole> h2ORoles,
                 Set<String> remoteAddresses,
                 Map<String, String> sessionMap,
                 ComputeRegion computeRegion) {
    super(UUID.randomUUID().toString(), createdAt, updatedAt, null, null, name);
    this.email = email;
    this.password = password;
    this.expired = expired;
    this.locked = locked;
    this.credentialsExpired = credentialsExpired;
    this.enabled = enabled;
    this.h2ORoles = h2ORoles;
    this.remoteAddresses = remoteAddresses;
    this.sessionMap = sessionMap;
    this.computeRegion = computeRegion;
    this.setCreatedBy(getId());
    this.setUpdatedBy(getId());
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

  public boolean isExpired() {
    return expired;
  }

  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public boolean isCredentialsExpired() {
    return credentialsExpired;
  }

  public void setCredentialsExpired(boolean credentialsExpired) {
    this.credentialsExpired = credentialsExpired;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    Account account = (Account) o;
    return Objects.equals(email, account.email) &&
            Objects.equals(password, account.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), email, password);
  }

  @Override
  public String toString() {
    return "Account{" +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", h2ORoles=" + h2ORoles +
        ", remoteAddresses=" + remoteAddresses +
        ", sessionMap=" + sessionMap +
        ", computeRegion=" + computeRegion +
        "} " + super.toString();
  }

  private String email;
  private String password;
  private boolean expired = false;
  private boolean locked = false;
  private boolean credentialsExpired = false;
  private boolean enabled = true;
  private Set<H2ORole> h2ORoles = new HashSet<>();
  private Set<String> remoteAddresses = new HashSet<>();
  private Map<String, String> sessionMap = new HashMap<>();
  private ComputeRegion computeRegion;
}
