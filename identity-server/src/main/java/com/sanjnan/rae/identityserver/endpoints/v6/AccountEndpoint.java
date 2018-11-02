/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.identityserver.endpoints.v6;

import com.sanjnan.rae.common.constants.SanjnanConstants;
import com.sanjnan.rae.common.exception.EntityNotFoundException;
import com.sanjnan.rae.identityserver.caching.AccountCacheService;
import com.sanjnan.rae.identityserver.caching.TenantCacheService;
import com.sanjnan.rae.identityserver.pojos.Account;
import com.sanjnan.rae.identityserver.pojos.Tenant;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by vinay on 1/4/16.
 */
@RestController
@RequestMapping(SanjnanConstants.V6_ACCOUNTS_ENDPOINT)
public class AccountEndpoint extends BaseEndpoint {

  Logger logger = Logger.getLogger(AccountEndpoint.class);

  @Autowired
  private AccountCacheService accountCacheService;
  @Autowired
  private TenantCacheService tenantCacheService;



  @Transactional(readOnly = true)
  @RequestMapping(value = "/{id_or_name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize(SanjnanConstants.ANNOTATION_ROLE_USER_ADMIN_AND_TENANT_ADMIN)
  public Optional<Account> getAccount(@PathVariable("tenant") String tenantDiscriminator, @PathVariable("id_or_name") String id_or_name) throws IOException {

    Optional<Tenant> tenantOptional = tenantCacheService.findByDiscriminator(tenantDiscriminator);
    if (tenantOptional.isPresent()) {
      Tenant tenant = tenantOptional.get();
      try {

        UUID id = UUID.fromString(id_or_name);
        logger.log(Level.INFO, "Retrieving account info for UUID = " + id_or_name);
        return accountCacheService.findOne(id);
      }
      catch(IllegalArgumentException iae) {
        logger.log(Level.INFO, "Retrieving account info for username = " + id_or_name);
        return accountCacheService.findByName(tenant.getId(), id_or_name);
      }
    }
    throw new EntityNotFoundException(String.format("%s tenant doesn't exist.", tenantDiscriminator));
  }

  @Transactional
  @PreAuthorize(SanjnanConstants.ANNOTATION_ROLE_ADMIN_AND_TENANT_ADMIN)
  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public Account createAccount(@PathVariable("tenant") String tenant, @RequestBody @Valid Account account) {
    return accountCacheService.createAccount(tenant, account);
  }

  @Transactional
  @PreAuthorize(SanjnanConstants.ANNOTATION_ROLE_USER_ADMIN_AND_TENANT_ADMIN)
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public Account updateAccount(@PathVariable("tenant") String tenant,
                               @PathVariable("id") UUID id,
                               @RequestBody @Valid Account account) throws InvocationTargetException, IllegalAccessException {
    return accountCacheService.updateAccount(tenant, id, account);
  }

  @Transactional
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize(SanjnanConstants.ANNOTATION_ROLE_ADMIN_AND_TENANT_ADMIN)
  public Account deleteAccount(@PathVariable("tenant") String tenant,
                               @PathVariable("id") UUID id) throws InvalidParameterSpecException {

    return accountCacheService.deleteAccount(tenant, id);
  }

}
