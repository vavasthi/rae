/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.exception;

import com.hubbleconnected.server.pojos.constants.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Created by maheshsapre on 28/01/16.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends SanjnanBaseException {

    /**
     * Instantiates a new Not found exception.
     *
     * @param errorCode the error code
     * @param Message   the message
     */
    public NotFoundException(int errorCode, String Message) {

    super(errorCode, ErrorCodes.NOT_FOUND_ERROR_MSG_PREFIX+Message);
  }

    /**
     * Instantiates a new Not found exception.
     *
     * @param message the message
     */
    public NotFoundException(String message) {
        super(message);
    }

}
