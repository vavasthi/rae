/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.identityserver;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.sanjnan.rae.identityserver.security.H2OAuditorAware;
import com.sanjnan.rae.identityserver.security.provider.H2OAuditingDateTimeProvider;
import com.sanjnan.rae.identityserver.services.DateTimeService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.logging.Logger;

/**
 * Created by vinay on 1/8/16.
 */
@SpringBootApplication(scanBasePackages = {"com.sanjnan.rae.identityserver"},
        exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@Configuration
@EnableTransactionManagement
//@EnableSwagger2
public class H2OIdentityManagerLauncher  extends SpringBootServletInitializer {

  Logger logger = Logger.getLogger(H2OIdentityManagerLauncher.class.getName());

  public static void main(String[] args) throws Exception {

    H2OIdentityManagerLauncher launcher = new H2OIdentityManagerLauncher();
    launcher
            .configure(new SpringApplicationBuilder(H2OIdentityManagerLauncher.class))
            .run(args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(H2OIdentityManagerLauncher.class);
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  DateTimeProvider dateTimeProvider(DateTimeService dateTimeService) {
    return new H2OAuditingDateTimeProvider(dateTimeService);
  }

  @Bean
  public Module jodaModule() {
    return new JodaModule();
  }
/*    @Bean
    RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = H2OConstants.H2ORole.ADMIN.getValue() + " > " + H2OConstants.H2ORole.FW_UPGRADE_ADMIN.getValue() + " ";
        hierarchy += ()
        roleHierarchy.setHierarchy();

    }*/

}