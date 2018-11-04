package com.sanjnan.rae.identityserver.pojos;

public class UsernameAndPassword {
  public UsernameAndPassword(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public UsernameAndPassword() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  private String username;
  private String password;
}
