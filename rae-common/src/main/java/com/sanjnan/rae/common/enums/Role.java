/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.enums;

/**
 * Created by maheshsapre on 04/04/16.
 */
public enum Role {
    USER("user"), // 0
    TALK_BACK_USER("talk_back_user"), // 1
    UPLOAD_SERVER("upload_server"), // 2
    FW_UPGRADE_USER("fw_upgrade_user"), // 3
    BP_SERVER("bp_server"), // 4
    WOWZA_USER("wowza_user"), // 5
    HELPDESK_AGENT("helpdesk_agent"), // 6
    FACTORY_USER("factory_user"), // 7
    TESTER("tester"), // 8
    MARKETING_ADMIN("marketing_admin"), // 9
    FW_UPGRADE_ADMIN("fw_upgrade_admin"), // 10
    POLICY_ADMIN("policy_admin"), // 11
    TENANT_ADMIN("tenant_admin"), // 12
    ADMIN("admin"), // 13
    DEVICE("device"); // 14

    private final String value;
    private final String role;

    Role(String value) {
        this.value = value;
        this.role = "\"hasRole('" + value + "')\")";
    }

    public static Role createFromString(String value) {
        for (Role r : Role.values()) {
            if (r.value.equalsIgnoreCase(value)) {
                return r;
            }
        }
        throw new IllegalArgumentException(value + " is not a valid value");
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
