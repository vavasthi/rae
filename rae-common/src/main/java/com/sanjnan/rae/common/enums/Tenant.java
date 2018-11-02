/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.enums;

/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.

/**
 * Created by maheshsapre on 04/04/16.
 */
public enum Tenant {
    VTECH("vtech"),
    HUBBLE("hubble"),;

    private final String value;
    private final String role;

    Tenant(String value) {
        this.value = value;
        this.role = "\"hasRole('" + value + "')\")";
    }

    public final String getValue() {
        return value;
    }

    public final String getRole() {
        return role;
    }

    public final String getRoleAnnotation() {
        return "hasRole('" + value + "')";
    }
}
