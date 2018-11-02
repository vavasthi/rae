package com.sanjnan.rae.identityserver.security;

import com.sanjnan.rae.identityserver.security.token.H2OPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * Created by vinay on 1/28/16.
 */

public class H2OAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return Optional.of(((H2OPrincipal) authentication.getPrincipal()).getName());
    }
}