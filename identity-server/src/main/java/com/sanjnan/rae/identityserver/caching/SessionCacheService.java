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

import com.sanjnan.rae.common.annotations.ORMCache;
import com.sanjnan.rae.common.caching.AbstractGeneralCacheService;
import com.sanjnan.rae.common.constants.SanjnanConstants;
import com.sanjnan.rae.common.utils.H2OUUIDStringPair;
import com.sanjnan.rae.identityserver.data.couchbase.SessionRepository;
import com.sanjnan.rae.identityserver.data.couchbase.TenantRepository;
import com.sanjnan.rae.identityserver.pojos.Account;
import com.sanjnan.rae.identityserver.pojos.Session;
import com.sanjnan.rae.identityserver.pojos.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by vinay on 2/22/16.
 */
@Service
@ORMCache(name = SanjnanConstants.H2O_SESSION_CACHE_NAME,
        expiry = SanjnanConstants.HALF_HOUR,
        prefix = SanjnanConstants.H2O_SESSION_CACHE_PREFIX)
public class SessionCacheService extends AbstractGeneralCacheService {

  @Autowired
  private SessionRepository sessionRepository;

  public Optional<Session> findOne(UUID id) {

    Session session = get(id, Session.class);
    if (session != null) {
      return Optional.of(session);
    }
    return storeToCacheAndReturn(sessionRepository.findById(id));
  }

  public Optional<Session> findByAuthToken(UUID tenantId, String authToken) {

    UUID sessionId = get(new H2OUUIDStringPair(tenantId, authToken), UUID.class);
    if (sessionId == null) {
      return storeToCacheAndReturn(sessionRepository.findByAuthToken(authToken));
    }
    return findOne(sessionId);
  }

  public void evictFromCache(Session session) {
    List<Object> keys = new ArrayList<>();
    keys.add(session.getId());
    keys.add(new H2OUUIDStringPair(session.getTenantId(), session.getAuthToken()));
    deleteObject(keys);
  }
  private void storeToCache(Session session) {

    Map<Object, Object> keyValuePairs = new HashMap<>();
    keyValuePairs.put(session.getId(), session);
    keyValuePairs.put(new H2OUUIDStringPair(session.getTenantId(), session.getAuthToken()), session.getId());
    storeObject(keyValuePairs);
  }

  private Optional<Session> storeToCacheAndReturn(Optional<Session> session) {

    if (session.isPresent()) {
      storeToCache(session.get());
    }
    return session;
  }
  public Session save(Session session) {
    evictFromCache(session);
    sessionRepository.save(session);
    return session;
  }

  public void delete(Session session) {

    evictFromCache(session);
    sessionRepository.delete(session);
  }
}
