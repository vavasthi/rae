package com.sanjnan.rae.identityserver.services;

import com.sanjnan.rae.common.exception.EntityAlreadyExistsException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TenantService {

  @Autowired
  private TenantRepository tenantRepository;

  Logger logger = Logger.getLogger(TenantService.class);

  public List<Tenant> listTenants() {
    List<Tenant> tenantList = new ArrayList<>();
    tenantRepository.findAll().forEach(e -> tenantList.add(e));
    return tenantList;
  }
  public Tenant getTenant(UUID id) {
    Optional<Tenant> tenantOptional = tenantRepository.findById(id.toString());
    if (tenantOptional.isPresent()) {
      Tenant tenant = tenantOptional.get();
      return tenant;
    }
    throw new EntityNotFoundException(id.toString());
  }
  public Optional<Tenant> getTenant(String discriminator) {
    return tenantRepository.findByDiscriminator(discriminator);
  }

  public Tenant createTenant(Tenant tenant) {

    Optional<Tenant> tenantOptional = tenantRepository.findByDiscriminator(tenant.getDiscriminator());
    if (tenantOptional.isPresent()) {
      throw new EntityAlreadyExistsException(String.format(SanjnanMessages.TENANT_ALREADY_EXISTS, tenant.getDiscriminator()));
    }
    if (tenant.getId() == null) {
      tenant.setId(UUID.randomUUID().toString());
    }
    tenantRepository.save(tenant);
    return tenant;
  }

  public Tenant updateTenant(UUID id, Tenant tenant) throws InvocationTargetException, IllegalAccessException {

    Optional<Tenant> tenantOptional = tenantRepository.findById(id.toString());
    if (tenantOptional.isPresent()) {

      Tenant storedTenant = tenantOptional.get();
      ObjectPatcher.diffAndPatch(Tenant.class, storedTenant, Tenant.class, tenant);
      tenantRepository.save(storedTenant);
    }
    throw new EntityNotFoundException(String.format(SanjnanMessages.TENANT_NOT_FOUND, tenant.getDiscriminator()));
  }
}
