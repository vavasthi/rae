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
import com.sanjnan.rae.common.security.provider.H2OAuditingDateTimeProvider;
import com.sanjnan.rae.common.services.DateTimeService;
import com.sanjnan.rae.identityserver.services.AccountService;
import com.sanjnan.rae.identityserver.services.SetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

/**
 * Created by vinay on 1/8/16.
 */
@EnableWebMvc
@SpringBootApplication(scanBasePackages = {"com.sanjnan.rae"}, exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class}
)
@Configuration
@EnableJpaRepositories("com.sanjnan.rae")
//@EnableSwagger2
public class SanjnanIdentityManagerLauncher extends SpringBootServletInitializer {

  @Autowired
  private SetupService setupService;
  @Autowired
  private DateTimeService dateTimeService;

  Logger logger = Logger.getLogger(SanjnanIdentityManagerLauncher.class.getName());


  public static void main(String[] args) throws Exception {

    SanjnanIdentityManagerLauncher launcher = new SanjnanIdentityManagerLauncher();
    launcher
            .configure(new SpringApplicationBuilder(SanjnanIdentityManagerLauncher.class))
            .run(args);
  }

  @PostConstruct
  public void initialize() {
    setupService.setup();
  }
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(SanjnanIdentityManagerLauncher.class);
  }

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  DateTimeProvider dateTimeProvider() {
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