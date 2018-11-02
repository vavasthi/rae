/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.utils;

import java.io.Serializable;

/**
 * Created by vavasthi on 23/2/16.
 */
public class H2OStringTriplet implements Serializable {
  public H2OStringTriplet(String first, String second, String third) {
    this.first = first;
    this.second = second;
    this.third = third;
  }

  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public String getSecond() {
    return second;
  }

  public void setSecond(String second) {
    this.second = second;
  }

  public String getThird() {
    return third;
  }

  public void setThird(String third) {
    this.third = third;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof H2OStringTriplet)) return false;

    H2OStringTriplet that = (H2OStringTriplet) o;

    if (first != null ? !first.equals(that.first) : that.first != null) return false;
    if (second != null ? !second.equals(that.second) : that.second != null) return false;
    return third != null ? third.equals(that.third) : that.third == null;

  }

  @Override
  public int hashCode() {
    int result = first != null ? first.hashCode() : 0;
    result = 31 * result + (second != null ? second.hashCode() : 0);
    result = 31 * result + (third != null ? third.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "H2OStringTriplet{" +
            "first='" + first + '\'' +
            ", second='" + second + '\'' +
            ", third='" + third + '\'' +
            '}';
  }

  private String first;
  private String second;
  private String third;
}
