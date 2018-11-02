/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.exception;

/**
 * The type Exception response builder.
 */
public class ExceptionResponseBuilder {

  /**
   * The Response.
   */
  ExceptionResponse response;

  /**
   * Instantiates a new Exception response builder.
   */
  public ExceptionResponseBuilder() {
    response = new ExceptionResponse();
  }

  /**
   * Sets status.
   *
   * @param status the status
   * @return the status
   */
  public ExceptionResponseBuilder setStatus(Integer status) {
    response.setStatus(status);
    return this;
  }

  /**
   * Sets code.
   *
   * @param code the code
   * @return the code
   */
  public ExceptionResponseBuilder setCode(Integer code) {
    response.setCode(code);
    return this;
  }

  /**
   * Sets message.
   *
   * @param message the message
   * @return the message
   */
  public ExceptionResponseBuilder setMessage(String message) {
    response.setMessage(message);
    return this;
  }

  /**
   * Sets more info.
   *
   * @param moreInfo the more info
   * @return the more info
   */
  public ExceptionResponseBuilder setMoreInfo(String moreInfo) {
    response.setMoreInfo(moreInfo);
    return this;
  }

  /**
   * Create exception response exception response.
   *
   * @return the exception response
   */
  public ExceptionResponse createExceptionResponse() {
    return response;
  }

}
