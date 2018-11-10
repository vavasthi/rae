/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.identityserver.security;

import com.sanjnan.rae.identityserver.couchbase.CouchbaseAccessTokenRepository;
import com.sanjnan.rae.identityserver.couchbase.CouchbaseRefreshTokenRepository;
import com.sanjnan.rae.identityserver.services.SanjnanTokenStore;
import com.sanjnan.rae.identityserver.services.SanjnanUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Created by vinay on 1/27/16.
 */

@Configuration
@EnableWebSecurity
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SanjnanSecurityConfiguration extends WebSecurityConfigurerAdapter {

/*  @Override
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
    web.ignoring().antMatchers("/");
    web.ignoring().antMatchers("/csrf");
    web.ignoring().antMatchers("/favicon.ico");
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
  }*/

  @Autowired
  private SanjnanUserDetailService userDetailService;


  @Autowired
  public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider());
  }
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider
            = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailService);
    authProvider.setPasswordEncoder(encoder());
    return authProvider;
  }

  @Override
  public void configure( WebSecurity web ) throws Exception {
    web.ignoring().antMatchers( HttpMethod.OPTIONS, "/**" );
/*    web.ignoring().antMatchers( HttpMethod.OPTIONS, "/**" );
    web.ignoring().antMatchers("/error");
    web.ignoring().antMatchers("/manage/health");
    web.ignoring().antMatchers("/swagger-ui.html");
    web.ignoring().antMatchers("/");
    web.ignoring().antMatchers("/csrf");
    web.ignoring().antMatchers("/favicon.ico");
    web.ignoring().antMatchers("/webjars/**");
    web.ignoring().antMatchers("/v2/api-docs/**");
    web.ignoring().antMatchers("/swagger-resources/**");*/
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/oauth/token").permitAll()
            .antMatchers("/api-docs/**").permitAll()
            .anyRequest().authenticated()
            .and().anonymous().disable();
  }
  @Bean
  public PasswordEncoder encoder(){
    return  new SCryptPasswordEncoder();
  }

  @Bean
  public FilterRegistrationBean corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    bean.setOrder(0);
    return bean;
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}