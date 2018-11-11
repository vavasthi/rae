package com.sanjnan.rae.product;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.sanjnan.rae.common.security.provider.H2OAuditingDateTimeProvider;
import com.sanjnan.rae.common.services.DateTimeService;
import com.sanjnan.rae.product.couchbase.ProductRepository;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
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

@EnableWebMvc
@SpringBootApplication(scanBasePackages = {"com.sanjnan.rae"}, exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class}
)
@Configuration
@EnableJpaRepositories("com.sanjnan.rae")
public class ProductLauncher extends SpringBootServletInitializer {

  @Autowired
  private DateTimeService dateTimeService;

  @Autowired
  private ProductRepository productRepository;


  public static void main(String[] args) {

    ProductLauncher launcher = new ProductLauncher();
    launcher
            .configure(new SpringApplicationBuilder(ProductLauncher.class))
            .run(args);
  }
  @PostConstruct
  public void initialize() {

  }
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ProductLauncher.class);
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
}
