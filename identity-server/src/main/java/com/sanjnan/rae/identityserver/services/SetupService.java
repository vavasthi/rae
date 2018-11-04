package com.sanjnan.rae.identityserver.services;

import com.sanjnan.rae.common.enums.Role;
import com.sanjnan.rae.identityserver.data.couchbase.AccountRepository;
import com.sanjnan.rae.identityserver.data.couchbase.TenantRepository;
import com.sanjnan.rae.identityserver.pojos.Account;
import com.sanjnan.rae.identityserver.pojos.H2ORole;
import com.sanjnan.rae.identityserver.pojos.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class SetupService {

  @Autowired
  private TenantRepository tenantRepository;
  @Autowired
  private AccountService accountService;

  public String setup() {

    Tenant tenant = new Tenant(null, null, "internal", "vinay@avasthi.com", "internal");
    tenantRepository.save(tenant);
    Account account = new Account();
    account.setTenantId(tenant.getId().toString());
    account.setName("superadmin");
    account.setEmail("apps@sanjnan.com");
    account.setPassword("Sanjnan1234#");
    account.setH2ORoles(new HashSet<>());
    account.getH2ORoles().add(new H2ORole(Role.SUPERADMIN.name()));
    account.getH2ORoles().add(new H2ORole(Role.USER.name()));
    accountService.createAccount("internal", account);
    return "Setup Complete!";
  }

  public String unsetup() {
    return "System initialized!";
  }
}
