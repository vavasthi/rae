/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.exception;

/**
 * The type Authorization exception.
 *
 * @author nikhilvs
 */
public class AuthorizationException extends SanjnanBaseException {

  /**
   * The Code.
   */
  Integer code;

  /**
   * Instantiates a new Authorization exception.
   *
   * @param message the message
   */
  public AuthorizationException(String message) {
    super(message);
  }

  /**
   * Instantiates a new Authorization exception.
   *
   * @param message the message
   * @param code    the code
   */
  public AuthorizationException(String message, Integer code) {
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
