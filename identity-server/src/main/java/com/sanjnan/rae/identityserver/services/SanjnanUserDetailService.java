package com.sanjnan.rae.identityserver.services;

import com.sanjnan.rae.common.pojos.Account;
import com.sanjnan.rae.common.pojos.SanjnanUserDetail;
import com.sanjnan.rae.identityserver.couchbase.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SanjnanUserDetailService implements UserDetailsService {

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    Optional<Account> accountOptional = accountRepository.findAccountByEmail(name);
    if (accountOptional.isPresent()) {
      Account account = accountOptional.get();

      return new SanjnanUserDetail(account);
    }
    throw new UsernameNotFoundException("Could not find the user " + name);
  }
}