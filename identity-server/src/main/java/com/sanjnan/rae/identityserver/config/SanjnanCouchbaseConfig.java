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
import com.sanjnan.rae.identityserver.pojos.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;

import java.util.List;

@Configuration
@EnableCouchbaseRepositories(basePackages = {"com.sanjnan.rae.identityserver.data.couchbase"})
public class SanjnanCouchbaseConfig extends AbstractCouchbaseConfiguration  {

  @Value("${com.sanjnan.rae.couchbase.bootstraphosts:localhost")
  private List<String> bootStrapHosts;

  @Value("${com.sanjnan.rae.couchbase.bucketName:default")
  private String bucketName;

  @Value("${com.sanjnan.rae.couchbase.bucketPassword:bucketPassword")
  private String bucketPassword;

  @Override
  protected List<String> getBootstrapHosts() {
    return bootStrapHosts;
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
    return couchbaseCluster().openBucket("identity", "");
  }
  @Bean
  public CouchbaseTemplate campusTemplate() throws Exception {
    CouchbaseTemplate template = new CouchbaseTemplate(
            couchbaseClusterInfo(), identityBucket(),
            mappingCouchbaseConverter(), translationService());
    template.setDefaultConsistency(getDefaultConsistency());
    return template;
  }
  @Override
  public void configureRepositoryOperationsMapping(
          RepositoryOperationsMapping baseMapping) {
    try {
      baseMapping.mapEntity(Account.class, campusTemplate());
    } catch (Exception e) {
      //custom Exception handling
    }
  }
}
