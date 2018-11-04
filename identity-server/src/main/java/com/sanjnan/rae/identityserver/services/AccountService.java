package com.sanjnan.rae.identityserver.services;

import com.sanjnan.rae.common.exception.EntityNotFoundException;
import com.sanjnan.rae.common.exception.TenantMismatchException;
import com.sanjnan.rae.common.utils.H2OPasswordEncryptionManager;
import com.sanjnan.rae.common.utils.ObjectPatcher;
import com.sanjnan.rae.identityserver.data.couchbase.AccountRepository;
import com.sanjnan.rae.identityserver.data.couchbase.TenantRepository;
import com.sanjnan.rae.identityserver.pojos.Account;
import com.sanjnan.rae.identityserver.pojos.Tenant;
import com.sanjnan.rae.identityserver.utils.SanjnanMessages;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private TenantRepository tenantRepository;

  Logger logger = Logger.getLogger(AccountService.class);


  public Optional<Account> getAccount(Tenant tenant, String id_or_name) {
      try {

        UUID id = UUID.fromString(id_or_name);
        logger.log(Level.INFO, "Retrieving account info for UUID = " + id_or_name);
        return accountRepository.findById(id.toString());
      }
      catch(IllegalArgumentException iae) {
        logger.log(Level.INFO, "Retrieving account info for username = " + id_or_name);
        return accountRepository.findAccountsByTenantIdAndName(tenant.getId().toString(), id_or_name);
      }
  }

  public Optional<Account> getAccount(String tenantDiscriminator, String id_or_name) {
    Optional<Tenant> tenantOptional = tenantRepository.findByDiscriminator(tenantDiscriminator);
    if (tenantOptional.isPresent()) {
      Tenant tenant = tenantOptional.get();
      try {

        UUID id = UUID.fromString(id_or_name);
        logger.log(Level.INFO, "Retrieving account info for UUID = " + id_or_name);
        return accountRepository.findById(id.toString());
      }
      catch(IllegalArgumentException iae) {
        logger.log(Level.INFO, "Retrieving account info for username = " + id_or_name);
        return accountRepository.findAccountsByTenantIdAndName(tenant.getId().toString(), id_or_name);
      }
    }
    throw new EntityNotFoundException(String.format(SanjnanMessages.TENANT_NOT_FOUND, tenantDiscriminator));
  }

  public Account createAccount(String discriminator, Account account) {

    Optional<Tenant> tenantOptional = tenantRepository.findByDiscriminator(discriminator);
    if (tenantOptional.isPresent()) {

      Tenant tenant = tenantOptional.get();
      if (account.getId() == null) {
        account.setId(UUID.randomUUID().toString());
      }
      if (account.getTenantId() == null) {
        account.setTenantId(tenant.getId());
      }
      account.setPassword(H2OPasswordEncryptionManager.INSTANCE.encrypt(account.getPassword()));
      accountRepository.save(account);
      account.setPassword(null);
      return account;
    }
    throw new EntityNotFoundException(String.format(SanjnanMessages.TENANT_NOT_FOUND, discriminator));
  }

  public Account updateAccount(String discriminator, UUID id, Account account) throws InvocationTargetException, IllegalAccessException {

    Optional<Tenant> tenantOptional = tenantRepository.findByDiscriminator(discriminator);
    if (tenantOptional.isPresent()) {

      Tenant tenant = tenantOptional.get();
      Optional<Account> accountOptional = accountRepository.findById(id.toString());
      if (accountOptional.isPresent()) {
        Account storedAccount = accountOptional.get();
        if (storedAccount.getTenantId().equals(tenant.getId())) {

          ObjectPatcher.diffAndPatch(Account.class, storedAccount, Account.class, account);
          accountRepository.save(storedAccount);
        }
        else {
          throw new TenantMismatchException(String.format(SanjnanMessages.TENANT_MISMATCH, account.getName(), tenant.getDiscriminator()));
        }
      }
      throw new EntityNotFoundException(String.format(SanjnanMessages.ACCOUNT_NOT_FOUND, account.getName()));
    }
    throw new EntityNotFoundException(String.format(SanjnanMessages.TENANT_NOT_FOUND, discriminator));
  }

  public Account deleteAccount(String discriminator, UUID id) {
    Optional<Tenant> tenantOptional = tenantRepository.findByDiscriminator(discriminator);
    if (tenantOptional.isPresent()) {

      Tenant tenant = tenantOptional.get();
      Optional<Account> accountOptional = accountRepository.findById(id.toString());
      if (accountOptional.isPresent()) {
        Account storedAccount = accountOptional.get();
        if (storedAccount.getTenantId().equals(tenant.getId())) {

          accountRepository.deleteById(id.toString());
        }
        else {
          throw new TenantMismatchException(String.format(SanjnanMessages.TENANT_MISMATCH, storedAccount.getName(), tenant.getDiscriminator()));
        }
      }
      throw new EntityNotFoundException(String.format(SanjnanMessages.ACCOUNT_NOT_FOUND, id.toString()));
    }
    throw new EntityNotFoundException(String.format(SanjnanMessages.TENANT_NOT_FOUND, discriminator));
  }

  public boolean validateCredentials(Tenant tenant, String username, String password) {
    Optional<Account> optionalAccount = getAccount(tenant, username);
    if (optionalAccount.isPresent()) {
      Account account = optionalAccount.get();
      return H2OPasswordEncryptionManager.INSTANCE.matches(password, account.getPassword());
    }
    throw new EntityNotFoundException(String.format(SanjnanMessages.ACCOUNT_NOT_FOUND, username));
  }
}
