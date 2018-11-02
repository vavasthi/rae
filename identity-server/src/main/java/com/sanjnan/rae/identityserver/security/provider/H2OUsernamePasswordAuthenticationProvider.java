/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.identityserver.security.provider;

import com.sanjnan.rae.identityserver.caching.AccountCacheService;
import com.sanjnan.rae.identityserver.caching.TenantCacheService;
import com.sanjnan.rae.identityserver.pojos.Account;
import com.sanjnan.rae.identityserver.pojos.Tenant;
import com.sanjnan.rae.identityserver.security.token.H2OPrincipal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by vinay on 2/3/16.
 */

public class H2OUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    public static final String INVALID_BACKEND_ADMIN_CREDENTIALS = "Invalid Backend Admin Credentials";
    private final static Logger logger = Logger.getLogger(H2OUsernamePasswordAuthenticationProvider.class);
    @Autowired
    private AccountCacheService accountCacheService;
    @Autowired
    private TenantCacheService tenantCacheService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        H2OPrincipal principal = (H2OPrincipal)authentication.getPrincipal();
        Optional<String> remoteAddr = principal.getRemoteAddr();
        Optional<String> discriminator = principal.getTenant();
        Optional<String> username = principal.getOptionalName();
        Optional<String> password = Optional.of((String) authentication.getCredentials());

        if (discriminator.isPresent() && username.isPresent() && password.isPresent()) {

            Optional<Tenant> tenantOptional = tenantCacheService.findByDiscriminator(discriminator.get());
            if (tenantOptional.isPresent()) {

                Tenant tenant = tenantOptional.get();
                logger.info("tenant = " + tenant.getDiscriminator() + " username " + username.toString());
                if (credentialsValid(tenant, username, password)) {

                    Optional<Account> accountOptional = accountCacheService.findByName(tenant.getId(), username.get());
                    if (accountOptional.isPresent()) {

                        Account account = accountOptional.get();
                        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
                        account.getH2ORoles().forEach(e -> grantedAuthorityList.add(e));
                        Authentication auth
                                = new UsernamePasswordAuthenticationToken(new H2OPrincipal(remoteAddr,
                                principal.getApplicationId(),
                                Optional.of(tenant.getDiscriminator()),
                                username),
                                password,
                                grantedAuthorityList);
                        return auth;
                    }
                }

            }
        }
        else {

            throw new BadCredentialsException(INVALID_BACKEND_ADMIN_CREDENTIALS);
        }
        throw new BadCredentialsException(INVALID_BACKEND_ADMIN_CREDENTIALS);
    }

    private boolean credentialsValid(Tenant tenant, Optional<String> username, Optional<String> password) {
        return accountCacheService.validateCredentials(tenant, username.get(), password.get());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}