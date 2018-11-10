/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.pojos;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by vinay on 1/28/16.
 */
public class H2ORole implements GrantedAuthority {
    private String authority;

    public H2ORole(String authority) {
        this.authority = authority;
    }

    public H2ORole() {
    }

    @Override
    public String toString() {
        return "H2ORole{" +
                "authority='" + authority + '\'' +
                '}';
    }

    @Override
    @JsonGetter("role")
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        H2ORole h2ORole = (H2ORole) o;

        return authority != null ? authority.equals(h2ORole.authority) : h2ORole.authority == null;

    }

    @Override
    public int hashCode() {
        return authority != null ? authority.hashCode() : 0;
    }
}
