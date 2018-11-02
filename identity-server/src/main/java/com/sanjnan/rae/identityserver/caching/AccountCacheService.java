/*
 * Copyright (c) 2018 Sanjnan Knowledge Technology Private Limited
 *
 * All Rights Reserved
 * This file contains software code that is proprietary and confidential.
 *  Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 *  Author: vavasthi
 */

package com.sanjnan.rae.identityserver.caching;

import com.sanjnan.rae.common.annotations.ORMCache;
import com.sanjnan.rae.common.caching.AbstractGeneralCacheService;
import com.sanjnan.rae.common.constants.SanjnanConstants;
import com.sanjnan.rae.common.utils.H2OPasswordEncryptionManager;
import com.sanjnan.rae.common.utils.H2OUUIDPair;
import com.sanjnan.rae.common.utils.H2OUUIDStringPair;
import com.sanjnan.rae.common.utils.ObjectPatcher;
import com.sanjnan.rae.identityserver.data.couchbase.AccountRepository;
import com.sanjnan.rae.identityserver.pojos.Account;
import com.sanjnan.rae.identityserver.pojos.Session;
import com.sanjnan.rae.identityserver.pojos.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by vinay on 2/22/16.
 */
@Service
@ORMCache(name = SanjnanConstants.H2O_ACCOUNT_CACHE_NAME,
        expiry = SanjnanConstants.SIX_HOURS,
        prefix = SanjnanConstants.H2O_ACCOUNT_CACHE_PREFIX)
public class AccountCacheService extends AbstractGeneralCacheService {

  @Autowired
  private AccountRepository accountRepository;

  public Optional<Account> findOne(UUID id) {

    Account account = get(id, Account.class);
    if (account != null) {
      return Optional.of(account);
    }
    return storeToCacheAndReturn(accountRepository.findById(id));
  }

  public Optional<Account> findByAuthToken(UUID tenantId, String authToken) {
    return null;
  }

  public Optional<Account> findByName(UUID tenantId, String name) {
    return null;
  }

  public void evictFromCache(Account account) {
    List<Object> keys = new ArrayList<>();
    keys.add(account.getId());
    keys.add(new H2OUUIDPair(account.getTenantId(), account.getId()));
    keys.add(new H2OUUIDStringPair(account.getTenantId(), account.getName()));
    keys.add(new H2OUUIDStringPair(account.getTenantId(), account.getEmail()));
    deleteObject(keys);
  }
  private void storeToCache(Account account) {

    Map<Object, Object> keyValuePairs = new HashMap<>();
    keyValuePairs.put(account.getId(), account);
    keyValuePairs.put(new H2OUUIDPair(account.getTenantId(), account.getId()), account.getId());
    keyValuePairs.put(new H2OUUIDStringPair(account.getTenantId(), account.getName()), account.getId());
    keyValuePairs.put(new H2OUUIDStringPair(account.getTenantId(), account.getEmail()), account.getId());
    storeObject(keyValuePairs);
  }

  private Optional<Account> storeToCacheAndReturn(Optional<Account> account) {

    if (account.isPresent()) {
      storeToCache(account.get());
    }
    return account;
  }
  public Account createAccount(String tenant, Account account) {
    account.setPassword(H2OPasswordEncryptionManager.INSTANCE.encrypt(account.getPassword()));
    save(account);
    account.setPassword(null);
    return account;
  }
  public Account updateAccount(String tenant, UUID id, Account account) throws InvocationTargetException, IllegalAccessException {
    Optional<Account> accountOptional = accountRepository.findById(id);
    if (accountOptional.isPresent()) {
      Account storedAccount = accountOptional.get();
      ObjectPatcher.diffAndPatch(Account.class, storedAccount, Account.class, account);
      if (account.getPassword() != null) {
        storedAccount.setPassword(H2OPasswordEncryptionManager.INSTANCE.encrypt(account.getPassword()));
      }
      accountRepository.save(storedAccount);
      return storedAccount;
    }
    return null;
  }
  public Account deleteAccount(String tenant, UUID id) {
    Optional<Account> accountOptional = accountRepository.findById(id);
    if (accountOptional.isPresent()) {
      Account account = accountOptional.get();
      accountRepository.deleteById(id);
      evictFromCache(account);
      return account;
    }
    return null;
  }

  public Account save(Account account) {
    evictFromCache(account);
    accountRepository.save(account);
    return account;
  }

  public void deleteToken(Account account, Session session) {
    account.getSessionMap().remove(session.getApplicationId());
    save(account);
  }

  public boolean validateCredentials(Tenant tenant, String username, String password) {

    Optional<Account> accountOptional = findByName(tenant.getId(), username);
    if (accountOptional.isPresent()) {
      Account account = accountOptional.get();
      if (account.getName().equalsIgnoreCase(username)) {
        return H2OPasswordEncryptionManager.INSTANCE.matches(password, account.getPassword());
      }
    }
    return false;
  }

}
