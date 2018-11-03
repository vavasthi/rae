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

import com.sanjnan.rae.identityserver.pojos.Tenant;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by vinay on 1/6/16.
 */
@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "tenant")
public interface TenantRepository extends CrudRepository<Tenant, UUID> {

  @View(designDocument = "tenant", viewName = "byDiscriminator")
  Optional<Tenant> findByDiscriminator(String discriminator);

}
