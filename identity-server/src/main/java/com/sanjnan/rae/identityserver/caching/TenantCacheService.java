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

import com.google.common.collect.Lists;
import com.sanjnan.rae.common.annotations.ORMCache;
import com.sanjnan.rae.common.caching.AbstractGeneralCacheService;
import com.sanjnan.rae.common.constants.SanjnanConstants;
import com.sanjnan.rae.common.exception.EntityNotFoundException;
import com.sanjnan.rae.common.utils.H2OUUIDPair;
import com.sanjnan.rae.common.utils.H2OUUIDStringPair;
import com.sanjnan.rae.common.utils.ObjectPatcher;
import com.sanjnan.rae.identityserver.data.couchbase.TenantRepository;
import com.sanjnan.rae.identityserver.data.couchbase.TenantRepository;
import com.sanjnan.rae.identityserver.pojos.Tenant;
import com.sanjnan.rae.identityserver.pojos.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by vinay on 2/22/16.
 */
@Service
@ORMCache(name = SanjnanConstants.H2O_TENANT_CACHE_NAME,
        expiry = SanjnanConstants.SIX_HOURS,
        prefix = SanjnanConstants.H2O_TENANT_CACHE_PREFIX)
public class TenantCacheService extends AbstractGeneralCacheService {

  @Autowired
  private TenantRepository tenantRepository;

  public Optional<Tenant> findOne(UUID id) {

    Tenant tenant = get(id, Tenant.class);
    if (tenant != null) {
      return Optional.of(tenant);
    }
    return storeToCacheAndReturn(tenantRepository.findById(id));
  }

  public Optional<Tenant> findByDiscriminator(String discriminator) {

    UUID tenantId = get(discriminator, UUID.class);
    if (tenantId == null) {
      return storeToCacheAndReturn(tenantRepository.findByDiscriminator(discriminator));
    }
    return findOne(tenantId);
  }

  public void evictFromCache(Tenant tenant) {
    List<Object> keys = new ArrayList<>();
    keys.add(tenant.getId());
    keys.add(tenant.getId());
    keys.add(tenant.getDiscriminator());
    deleteObject(keys);
  }
  private void storeToCache(Tenant tenant) {

    Map<Object, Object> keyValuePairs = new HashMap<>();
    keyValuePairs.put(tenant.getId(), tenant);
    keyValuePairs.put(tenant.getDiscriminator(), tenant.getId());
    storeObject(keyValuePairs);
  }

  private Optional<Tenant> storeToCacheAndReturn(Optional<Tenant> tenant) {

    if (tenant.isPresent()) {
      storeToCache(tenant.get());
    }
    return tenant;
  }

  public List<Tenant> listTenants() {

    return Lists.newArrayList(tenantRepository.findAll());
  }

  public Tenant createTenant(Tenant tenant) {
    return tenantRepository.save(tenant);
  }

  public Tenant updateTenant(UUID id, Tenant tenant) throws InvocationTargetException, IllegalAccessException {
    Optional<Tenant> tenantOptional = findOne(id);
    if (tenantOptional.isPresent()) {
      Tenant storedTenant = tenantOptional.get();
      ObjectPatcher.diffAndPatch(Tenant.class, storedTenant, Tenant.class, tenant);
      tenantRepository.save(tenant);
      return storedTenant;
    }
    throw new EntityNotFoundException(id.toString());
  }

  public Tenant deleteTenant(UUID id) {
    Optional<Tenant> tenantOptional = findOne(id);
    if (tenantOptional.isPresent()) {
      Tenant tenant = tenantOptional.get();
      tenantRepository.deleteById(id);
      return tenant;
    }
    throw new EntityNotFoundException(id.toString());
  }
}
