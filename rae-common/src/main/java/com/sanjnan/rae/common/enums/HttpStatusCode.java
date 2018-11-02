/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.enums;

/**
 * Created by userq on 18/2/16.
 */
public enum HttpStatusCode {

  /**
   * The Success.
   */
// http query status
  SUCCESS(200),

  /**
   * The Parameters insufficient.
   */
//client error code
  PARAMETERS_INSUFFICIENT(400),
  /**
   * Camera not registered in portal http status code.
   */
  CAMERA_NOT_REGISTERED_IN_PORTAL(401),
  /**
   * Http client conflict error code http status code.
   */
  HTTP_CLIENT_CONFLICT_ERROR_CODE(409),

  /**
   * The Internal server error.
   */
//server error code
  INTERNAL_SERVER_ERROR(500);

  private int value;

  HttpStatusCode(int value) {
    this.value = value;
  }

}
