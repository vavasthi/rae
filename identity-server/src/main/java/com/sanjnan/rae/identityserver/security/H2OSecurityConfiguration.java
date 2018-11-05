/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.identityserver.security;

import com.sanjnan.rae.common.constants.SanjnanConstants;
import com.sanjnan.rae.common.enums.Role;
import com.sanjnan.rae.identityserver.security.filters.H2OAuthenticationFilter;
import com.sanjnan.rae.identityserver.security.provider.H2OTokenAuthenticationProvider;
import com.sanjnan.rae.identityserver.security.provider.H2OUsernamePasswordAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by vinay on 1/27/16.
 */

@Configuration
@EnableWebSecurity
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class H2OSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(h2oUsernamePasswordAuthenticationProvider()).
        authenticationProvider(tokenAuthenticationProvider());
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers(SanjnanConstants.V1_SETUP_ENDPOINT);
    web.ignoring().antMatchers("/error");
    web.ignoring().antMatchers("/manage/health");
    web.ignoring().antMatchers("/swagger-ui.html");
    web.ignoring().antMatchers("/webjars/**");
    web.ignoring().antMatchers("/v2/api-docs/**");
    web.ignoring().antMatchers("/swagger-resources/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().
        sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
        and().
        authorizeRequests().
            antMatchers(actuatorEndpoints()).hasRole(Role.ADMIN.getValue()).
        anyRequest().authenticated().
        and().
        anonymous().disable().
        exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint());

    http.addFilterBefore(new H2OAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
  }

  private String[] actuatorEndpoints() {
    return new String[]{
            SanjnanConstants.AUTOCONFIG_ENDPOINT,
            SanjnanConstants.BEANS_ENDPOINT,
            SanjnanConstants.CONFIGPROPS_ENDPOINT,
            SanjnanConstants.ENV_ENDPOINT,
            SanjnanConstants.MAPPINGS_ENDPOINT,
            SanjnanConstants.METRICS_ENDPOINT,
            SanjnanConstants.SHUTDOWN_ENDPOINT
    };
  }

  @Bean
  public AuthenticationEntryPoint unauthorizedEntryPoint() {
    return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
  }

  @Bean
  public AuthenticationProvider h2oUsernamePasswordAuthenticationProvider() {
    return new H2OUsernamePasswordAuthenticationProvider();
  }

  @Bean
  public AuthenticationProvider tokenAuthenticationProvider() {
    return new H2OTokenAuthenticationProvider();
  }

}