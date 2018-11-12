package com.sanjnan.rae.common.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.*;

@Document
public class SanjnanClientDetails {
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }

  public Set<String> getResourceIds() {
    return resourceIds;
  }

  public void setResourceIds(Set<String> resourceIds) {
    this.resourceIds = resourceIds;
  }

  public boolean isSecretRequired() {
    return secretRequired;
  }

  public void setSecretRequired(boolean secretRequired) {
    this.secretRequired = secretRequired;
  }

  public boolean isScoped() {
    return scoped;
  }

  public void setScoped(boolean scoped) {
    this.scoped = scoped;
  }

  public Set<String> getScope() {
    return scope;
  }

  public void setScope(Set<String> scope) {
    this.scope = scope;
  }

  public Set<String> getAuthorizedGrantTypes() {
    return authorizedGrantTypes;
  }

  public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
    this.authorizedGrantTypes = authorizedGrantTypes;
  }

  public Set<String> getRegisteredRedirectUri() {
    return registeredRedirectUri;
  }

  public void setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
    this.registeredRedirectUri = registeredRedirectUri;
  }

  public Collection<String> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Collection<String> authorities) {
    this.authorities = authorities;
  }

  public Integer getAccessTokenValiditySeconds() {
    return accessTokenValiditySeconds;
  }

  public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
    this.accessTokenValiditySeconds = accessTokenValiditySeconds;
  }

  public Integer getRefreshTokenValiditySeconds() {
    return refreshTokenValiditySeconds;
  }

  public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
    this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
  }

  public boolean isAutoApprove() {
    return autoApprove;
  }

  public void setAutoApprove(boolean autoApprove) {
    this.autoApprove = autoApprove;
  }

  public Map<String, Object> getAdditionalInformation() {
    return additionalInformation;
  }

  public void setAdditionalInformation(Map<String, Object> additionalInformation) {
    this.additionalInformation = additionalInformation;
  }

  @Id
  @NotNull
  private String id;

  @NotNull
  private String clientId;
  private String clientSecret;
  private Set<String> resourceIds = new HashSet<>();
  private boolean secretRequired;
  private boolean scoped;
  private Set<String> scope = new HashSet<>();
  private Set<String> authorizedGrantTypes = new HashSet<>();
  private Set<String> registeredRedirectUri = new HashSet<>();
  private Collection<String> authorities = new HashSet<>();
  private Integer accessTokenValiditySeconds;
  private  Integer refreshTokenValiditySeconds;
  private boolean autoApprove;
  private Map<String, Object> additionalInformation = new HashMap<>();
}
