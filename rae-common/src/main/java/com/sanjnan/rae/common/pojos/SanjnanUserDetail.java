package com.sanjnan.rae.common.pojos;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class SanjnanUserDetail implements UserDetails {

  private Account account;

  public SanjnanUserDetail(Account account) {
    this.account = account;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return account.getH2ORoles().stream().collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return account.getPassword();
  }

  @Override
  public String getUsername() {
    return account.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return !account.isExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return !account.isLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !account.isCredentialsExpired();
  }

  @Override
  public boolean isEnabled() {
    return account.isEnabled();
  }
}
