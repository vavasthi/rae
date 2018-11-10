package com.sanjnan.rae.identityserver.services;

import com.sanjnan.rae.common.exception.EntityNotFoundException;
import com.sanjnan.rae.common.utils.H2OPasswordEncryptionManager;
import com.sanjnan.rae.common.utils.ObjectPatcher;
import com.sanjnan.rae.common.pojos.Account;
import com.sanjnan.rae.identityserver.utils.SanjnanMessages;
import com.sanjnan.rae.oauth2.couchbase.AccountRepository;
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
  private SetupService setupService;

  Logger logger = Logger.getLogger(AccountService.class);


  public Optional<Account> getAccount(String id_or_email) {

    try {

      UUID id = UUID.fromString(id_or_email);
      logger.log(Level.INFO, "Retrieving account info for UUID = " + id_or_email);
      Optional<Account> accountOptional = accountRepository.findById(id.toString());
      if (accountOptional.isPresent()) {
        return accountOptional;
      }
    }
    catch(IllegalArgumentException iae) {
      logger.log(Level.INFO, "Retrieving account info for username = " + id_or_email);
      Optional<Account> accountOptional = accountRepository.findAccountByEmail(id_or_email);
      if (accountOptional.isPresent()) {
        return accountOptional;
      }
    }
    throw new EntityNotFoundException(String.format(SanjnanMessages.ACCOUNT_NOT_FOUND, id_or_email));
  }

  public Account createAccount(Account account) {


    if (account.getId() == null) {
      account.setId(UUID.randomUUID().toString());
    }
    account.setPassword(H2OPasswordEncryptionManager.INSTANCE.encrypt(account.getPassword()));
    accountRepository.save(account);
    account.setPassword(null);
    return account;
  }

  public Account updateAccount(UUID id, Account account) throws InvocationTargetException, IllegalAccessException {

    Optional<Account> accountOptional = accountRepository.findById(id.toString());
    if (accountOptional.isPresent()) {
      Account storedAccount = accountOptional.get();
      ObjectPatcher.diffAndPatch(Account.class, storedAccount, Account.class, account);
      accountRepository.save(storedAccount);
    }
    throw new EntityNotFoundException(String.format(SanjnanMessages.ACCOUNT_NOT_FOUND, account.getName()));
  }

  public Account deleteAccount(UUID id) {
    Optional<Account> accountOptional = accountRepository.findById(id.toString());
    if (accountOptional.isPresent()) {
      Account storedAccount = accountOptional.get();
      accountRepository.deleteById(id.toString());
      return storedAccount;
    }
    throw new EntityNotFoundException(String.format(SanjnanMessages.ACCOUNT_NOT_FOUND, id.toString()));
  }

  public boolean validateCredentials(String username, String password) {
    Optional<Account> optionalAccount = getAccount(username);
    if (optionalAccount.isPresent()) {
      Account account = optionalAccount.get();
      return H2OPasswordEncryptionManager.INSTANCE.matches(password, account.getPassword());
    }
    throw new EntityNotFoundException(String.format(SanjnanMessages.ACCOUNT_NOT_FOUND, username));
  }

}
