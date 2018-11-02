/*
 * Copyright (c) 2018 Sanjnan Knowledge Technology Private Limited
 *
 * All Rights Reserved
 * This file contains software code that is proprietary and confidential.
 *  Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 *  Author: vavasthi
 */

package com.sanjnan.rae.identityserver.data.couchbase;

import com.sanjnan.rae.identityserver.pojos.Account;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by vinay on 1/6/16.
 */
@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "account")
public interface AccountRepository extends CrudRepository<Account, UUID> {
    @View(designDocument = "account", viewName = "byTenantIdAndName")
    Optional<Account> findAccountByTenantIdAndAndName(UUID tenantId, String name);
}
