/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.exception;

/**
 * The type Bad request exception.
 *
 * @author nikhilvs
 */
/*
* Add extra attributes as per request
*/
public final class BadRequestException extends SanjnanBaseException {

  /**
   * The Code.
   */
  Integer code;

  /**
   * Instantiates a new Bad request exception.
   *
   * @param message the message
   */
  public BadRequestException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Bad request exception.
   *
   * @param message the message
   * @param code    the code
   */
  public BadRequestException(String message, Integer code) {
    super(message);
    this.code = code;
  }

  /**
   * Gets code.
   *
   * @return the code
   */
  public Integer getCode() {
    return code;
  }


}

