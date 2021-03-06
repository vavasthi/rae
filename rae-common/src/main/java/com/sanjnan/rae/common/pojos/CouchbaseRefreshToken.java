package com.sanjnan.rae.common.pojos;

import com.sanjnan.rae.common.converters.SerializableObjectConverter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@Document
public class CouchbaseRefreshToken {

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTokenId() {
    return tokenId;
  }

  public void setTokenId(String tokenId) {
    this.tokenId = tokenId;
  }

  public OAuth2RefreshToken getToken() {
    return token;
  }

  public void setToken(OAuth2RefreshToken token) {
    this.token = token;
  }

  public void setAuthentication(String authentication) {
    this.authentication = authentication;
  }

  public OAuth2Authentication getAuthentication() {
    return SerializableObjectConverter.deserialize(authentication);
  }

  public void setAuthentication(OAuth2Authentication authentication) {
    this.authentication = SerializableObjectConverter.serialize(authentication);
  }
  @Id
  private String id;
  private String tokenId;
  private OAuth2RefreshToken token;
  private String authentication;
}