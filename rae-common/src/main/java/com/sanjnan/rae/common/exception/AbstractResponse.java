/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.exception;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sanjnan.rae.common.constants.SanjnanConstants;

import java.net.HttpURLConnection;

/**
 * The type Abstract response.
 *
 * @author nikhilvs9999
 */
@JsonInclude(Include.NON_NULL)
public abstract class AbstractResponse {

  /**
   * The Status.
   */
  Integer status= HttpURLConnection.HTTP_OK;
  /**
   * The Code.
   */
  Integer code;
  /**
   * The Message.
   */
  String message = SanjnanConstants.MSG_SUCCESS;
  /**
   * The More info.
   */
  String moreInfo;

  /**
   * Gets status.
   *
   * @return the status
   */
  @JsonGetter("status")
  public Integer getStatus() {
    return status;
  }

  /**
   * Sets status.
   *
   * @param status the status
   */
  public void setStatus(Integer status) {
    this.status = status;
  }

  /**
   * Gets code.
   *
   * @return the code
   */
  @JsonGetter("code")
  public Integer getCode() {
    return code;
  }

  /**
   * Sets code.
   *
   * @param code the code
   */
  public void setCode(Integer code) {
    this.code = code;
  }

  /**
   * Gets message.
   *
   * @return the message
   */
  @JsonGetter("message")
  public String getMessage() {
    return message;
  }

  /**
   * Sets message.
   *
   * @param message the message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets more info.
   *
   * @return the more info
   */
  @JsonGetter("more_info")
  public String getMoreInfo() {
    return moreInfo;
  }

  /**
   * Sets more info.
   *
   * @param moreInfo the more info
   */
  public void setMoreInfo(String moreInfo) {
    this.moreInfo = moreInfo;
  }

  /**
   * Gets data.
   *
   * @return the data
   */
  @JsonGetter("data")
  public abstract Object getData();

  /**
   * Sets data.
   *
   * @param data the data
   */
  public abstract void setData(Object data);

}
