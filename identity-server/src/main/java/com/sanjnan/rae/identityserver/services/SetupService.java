package com.sanjnan.rae.identityserver.services;

import com.sanjnan.rae.identityserver.data.couchbase.TenantRepository;
import com.sanjnan.rae.identityserver.pojos.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SetupService {

  @Autowired
  private TenantRepository tenantRepository;
  public String setup() {

    Tenant tenant = new Tenant(null, null, "internal", "vinay@avasthi.com", "internal");
    tenantRepository.save(tenant);
    return "Setup Complete!";
  }

  public String unsetup() {
    return "System initialized!";
  }
}
