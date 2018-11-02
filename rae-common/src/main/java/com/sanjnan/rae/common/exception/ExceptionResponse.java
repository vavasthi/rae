/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.exception;

/**
 * The type Exception response.
 *
 * @author nikhilvs9999
 */
public class ExceptionResponse extends AbstractResponse {

  /**
   * Instantiates a new Exception response.
   */
  public ExceptionResponse() {

  }
  // Since data is not required here , we are returning null

  @Override
  public Object getData() {
    return null;
  }

  @Override
  public void setData(Object data) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
}
