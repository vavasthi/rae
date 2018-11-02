/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.exception;


import com.sanjnan.rae.common.constants.SanjnanConstants;

/**
 * Created by vinay on 1/11/16.
 */
public class PatchingException extends SanjnanBaseException {

    public PatchingException(Class<?> cls, Object obj) {
        super(String.format("Object %s is not an instance of class %s.", obj.toString(), cls.getName()));
    }

    public PatchingException(Class<?> cls, String name) {
        super(String.format("Class %s doesn't contain a setter method %s.", cls.getName(), name));
    }
}
