/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.common.caching;

import java.io.Serializable;

/**
 * Created by vinay on 5/13/16.
 */
public class CacheKeyPrefix implements Serializable {

  public CacheKeyPrefix(final String prefix, final Object key) {
    this.prefix = prefix;
    this.key = key;
  }

  public CacheKeyPrefix() {
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(final String prefix) {
    this.prefix = prefix;
  }

  public Object getKey() {
    return key;
  }

  public void setKey(final Object key) {
    this.key = key;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (!(o instanceof CacheKeyPrefix)) return false;

    final CacheKeyPrefix that = (CacheKeyPrefix) o;

    if (prefix != null ? !prefix.equals(that.prefix) : that.prefix != null) return false;
    return key != null ? key.equals(that.key) : that.key == null;

  }

  @Override
  public int hashCode() {
    int result = prefix != null ? prefix.hashCode() : 0;
    result = 31 * result + (key != null ? key.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "CacheKeyPrefix{" +
        "prefix='" + prefix + '\'' +
        ", key=" + key +
        '}';
  }

  private String prefix;
  private Object key;
}
