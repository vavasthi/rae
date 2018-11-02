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
public enum ClientTypes {
    IOS("ios", 1),
    ANDROID("android", 3),
    BROWSER("browser", 2);

    private final String typeString;
    private final int type;

    ClientTypes(String typeString, int type) {
        this.type = type;
        this.typeString = typeString;
    }

    /**
     * Create mode.
     *
     * @param typeString the mode string
     * @return the mode
     */
    public static ClientTypes create(String typeString) {
        for (ClientTypes m : values()) {
            if (m.typeString.equalsIgnoreCase(typeString)) {
                return m;
            }
        }
        throw new IllegalArgumentException(String.format("%s is not a valid client type", typeString));
    }

    /**
     * Create mode.
     *
     * @param type the mode
     * @return the mode
     */
    public static ClientTypes create(int type) {
        for (ClientTypes m : values()) {
            if (m.type == type) {
                return m;
            }
        }
        throw new IllegalArgumentException(String.format("%d is not a valid client type", type));
    }

    public String toString() {
        return typeString;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public Integer getValue() {
        return type;
    }
}
