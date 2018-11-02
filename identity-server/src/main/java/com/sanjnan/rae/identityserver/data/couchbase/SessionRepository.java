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

import com.sanjnan.rae.identityserver.pojos.Session;
import com.sanjnan.rae.identityserver.pojos.Tenant;
import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by vinay on 1/6/16.
 */
public interface SessionRepository extends CrudRepository<Session, UUID> {

  @View(designDocument = "session", viewName = "byAuthToken")
  Optional<Session> findByAuthToken(String authToken);

}
