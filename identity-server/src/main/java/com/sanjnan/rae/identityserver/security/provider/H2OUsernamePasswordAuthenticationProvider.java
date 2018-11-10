/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.identityserver.security.provider;

import com.sanjnan.rae.common.pojos.Account;
import com.sanjnan.rae.common.pojos.Session;
import com.sanjnan.rae.common.security.token.H2OPrincipal;
import com.sanjnan.rae.identityserver.services.AccountService;
import com.sanjnan.rae.identityserver.services.H2OTokenService;
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
    private AccountService accountService;
    @Autowired
    private H2OTokenService tokenService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        H2OPrincipal principal = (H2OPrincipal)authentication.getPrincipal();
        Optional<String> remoteAddr = principal.getRemoteAddr();
        Optional<String> username = principal.getOptionalName();
        Optional<String> clientId = principal.getApplicationId();
        Optional<String> password = (Optional<String>) authentication.getCredentials();

        if (username.isPresent() && password.isPresent()) {

                if (credentialsValid(username, password)) {

                    Optional<Account> accountOptional = accountService.getAccount(username.get());
                    if (accountOptional.isPresent()) {

                        Account account = accountOptional.get();
                        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
                        account.getH2ORoles().forEach(e -> grantedAuthorityList.add(e));
                        Session session = tokenService.create(remoteAddr.get(), clientId.get(), username.get(), password.get());
                        Authentication auth
                                = new UsernamePasswordAuthenticationToken(new H2OPrincipal(remoteAddr,
                                principal.getApplicationId(),
                                username),
                                password,
                                grantedAuthorityList);
                        return auth;
                    }
                }

            }
        throw new BadCredentialsException(INVALID_BACKEND_ADMIN_CREDENTIALS);
    }

    private boolean credentialsValid(Optional<String> username, Optional<String> password) {
        return accountService.validateCredentials(username.get(), password.get());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}