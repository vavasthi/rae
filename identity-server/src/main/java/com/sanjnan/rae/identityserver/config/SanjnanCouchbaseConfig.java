/*
 * Copyright (c) 2018 Sanjnan Knowledge Technology Private Limited
 *
 * All Rights Reserved
 * This file contains software code that is proprietary and confidential.
 *  Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 *  Author: vavasthi
 */

package com.sanjnan.rae.identityserver.config;

import com.couchbase.client.java.Bucket;
import com.sanjnan.rae.common.pojos.Account;
import com.sanjnan.rae.common.pojos.SanjnanClientDetails;
import com.sanjnan.rae.common.pojos.Session;
import com.sanjnan.rae.identityserver.tokens.CouchbaseAccessToken;
import com.sanjnan.rae.identityserver.tokens.CouchbaseRefreshToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableCouchbaseRepositories(basePackages = {"com.sanjnan.rae.identityserver.couchbase"})
@EnableJpaRepositories
public class SanjnanCouchbaseConfig extends AbstractCouchbaseConfiguration  {

  @Value("${com.sanjnan.rae.couchbase.bootstraphosts:localhost:8091}")
  private String bootStrapHosts;

  @Value("${com.sanjnan.rae.couchbase.bucketName:identity}")
  private String bucketName;

  @Value("${com.sanjnan.rae.couchbase.bucketPassword:identity123}")
  private String bucketPassword;

  @Value("${com.sanjnan.rae.couchbase.username:identity}")
  private String couchbaseUsername;

  @Value("${com.sanjnan.rae.couchbase.password:identity123}")
  private String couchBasePassword;

  @Override
  protected List<String> getBootstrapHosts() {
    return  Arrays.asList(bootStrapHosts.split(","));
  }

  @Override
  protected String getBucketName() {
    return bucketName;
  }

  @Override
  protected String getBucketPassword() {
    return bucketPassword;
  }

  @Bean
  public Bucket identityBucket() throws Exception {
    return couchbaseCluster().openBucket("identity");
  }
  @Bean
  public Bucket accountBucket() throws Exception {

    return couchbaseCluster().openBucket("accounts");
  }
  @Bean
  public Bucket sessionBucket() throws Exception {
    return couchbaseCluster().openBucket("sessions");
  }
  @Bean
  public CouchbaseTemplate campusTemplate() throws Exception {
    CouchbaseTemplate template = new CouchbaseTemplate(
            couchbaseCluster().authenticate(couchbaseUsername, couchBasePassword).clusterManager().info(),
            identityBucket(),
            mappingCouchbaseConverter(), translationService());
    template.setDefaultConsistency(getDefaultConsistency());
    return template;
  }
  @Bean
  public CouchbaseTemplate accountTemplate() throws Exception {
    CouchbaseTemplate template
            = new CouchbaseTemplate(couchbaseCluster().authenticate(couchbaseUsername, couchBasePassword).clusterManager().info(),
            accountBucket(),
            mappingCouchbaseConverter(),
            translationService());
    template.setDefaultConsistency(getDefaultConsistency());
    return template;
  }
  @Bean
  public CouchbaseTemplate sessionTemplate() throws Exception {
    CouchbaseTemplate template = new CouchbaseTemplate(
            couchbaseCluster().authenticate(couchbaseUsername, couchBasePassword).clusterManager().info(),
            sessionBucket(),
            mappingCouchbaseConverter(), translationService());
    template.setDefaultConsistency(getDefaultConsistency());
    return template;
  }
  @Override
  public void configureRepositoryOperationsMapping(
          RepositoryOperationsMapping baseMapping) {
    try {
      baseMapping.mapEntity(Account.class, accountTemplate());
      baseMapping.mapEntity(Session.class, sessionTemplate());
      baseMapping.mapEntity(CouchbaseAccessToken.class, sessionTemplate());
      baseMapping.mapEntity(CouchbaseRefreshToken.class, sessionTemplate());
      baseMapping.mapEntity(SanjnanClientDetails.class, sessionTemplate());
    } catch (Exception e) {
      //custom Exception handling
    }
  }
}
