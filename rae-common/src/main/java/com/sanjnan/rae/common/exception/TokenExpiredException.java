/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.exception;

import com.hubbleconnected.server.pojos.constants.ErrorCodes;
import com.sanjnan.rae.common.constants.SanjnanConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenExpiredException extends SanjnanBaseException {

    /**
     * Instantiates a new Token expired exception.
     *
     * @param errorCode the error code
     * @param Message   the message
     */
    public TokenExpiredException(int errorCode, String Message) {

    super(errorCode, ErrorCodes.TOKEN_EXPIRED_ERROR_CODE + ": " + Message);
  }

}
