/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by subrat on 29/2/16.
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableException extends SanjnanBaseException {

    /**
     * Instantiates a new Unprocessable exception.
     *
     * @param errorCode the error code
     * @param message   the message
     * @param throwable the throwable
     */
    public UnprocessableException(int errorCode, String message, Throwable throwable) {

        super(message, throwable);
        this.setErrorCode(errorCode);
    }

    public UnprocessableException(int errorCode, String message) {
        super(message);
        this.setErrorCode(errorCode);
    }

    public UnprocessableException(String message) {
        super(message);
    }
}
