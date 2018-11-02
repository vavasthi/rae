/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.utils;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by vavasthi on 23/2/16.
 */
public class H2OUUIDStringPair  implements Serializable {
  public H2OUUIDStringPair() {
  }

  public H2OUUIDStringPair(UUID first, String second) {
    this.first = first;
    this.second = second;
  }

  public UUID getFirst() {
    return first;
  }

  public void setFirst(UUID first) {
    this.first = first;
  }

  public String getSecond() {
    return second;
  }

  public void setSecond(String second) {
    this.second = second;
  }

  @Override
  public String toString() {
    return "H2OUUIDStringPair{" +
        "first=" + first +
        ", second='" + second + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    H2OUUIDStringPair that = (H2OUUIDStringPair) o;

    if (first != null ? !first.equals(that.first) : that.first != null) return false;
    return second != null ? second.equals(that.second) : that.second == null;

  }

  @Override
  public int hashCode() {
    int result = first != null ? first.hashCode() : 0;
    result = 31 * result + (second != null ? second.hashCode() : 0);
    return result;
  }

  private UUID first;
  private String second;
}
