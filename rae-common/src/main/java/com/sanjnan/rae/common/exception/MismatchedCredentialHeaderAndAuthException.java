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
 * Created by vinay on 2/4/16.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class MismatchedCredentialHeaderAndAuthException  extends SanjnanBaseException {

  /**
   * Instantiates a new Mismatched credential header and auth exception.
   */
  public MismatchedCredentialHeaderAndAuthException(){
    super();
  }

  /**
   * Instantiates a new Mismatched credential header and auth exception.
   *
   * @param message the message
   */
  public MismatchedCredentialHeaderAndAuthException(String message){
    super(message);
  }

}
