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
public class H2OStringIntPair implements Serializable {
  public H2OStringIntPair(String first, int second) {
    this.first = first;
    this.second = second;
  }

  public H2OStringIntPair() {
  }

  public String getFirst() {
    return first;
  }

  public void setFirst(String first) {
    this.first = first;
  }

  public int getSecond() {
    return second;
  }

  public void setSecond(int second) {
    this.second = second;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    H2OStringIntPair that = (H2OStringIntPair) o;

    if (second != that.second) return false;
    return first != null ? first.equals(that.first) : that.first == null;

  }

  @Override
  public int hashCode() {
    int result = first != null ? first.hashCode() : 0;
    result = 31 * result + second;
    return result;
  }

  private String first;
  private int second;
}
