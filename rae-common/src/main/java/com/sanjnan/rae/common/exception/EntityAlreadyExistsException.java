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
 * Created by vavasthi on 27/1/16.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class EntityAlreadyExistsException extends SanjnanBaseException {

  /**
   * The Code.
   */
  Integer code;

  /**
   * Instantiates a new Entity already exists exception.
   */
  public EntityAlreadyExistsException(){
    super();
  }

  /**
   * Instantiates a new Entity already exists exception.
   *
   * @param message the message
   */
  public EntityAlreadyExistsException(String message){
    super(message);
  }

  /**
   * Instantiates a new Entity already exists exception.
   *
   * @param message the message
   * @param code    the code
   */
  public EntityAlreadyExistsException(String message, Integer code){
    super(code,message);
    this.code=code;
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
