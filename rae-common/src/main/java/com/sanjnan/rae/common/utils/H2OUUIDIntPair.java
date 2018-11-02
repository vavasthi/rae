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
 * Created by praveen on 31/3/16.
 */
public class H2OUUIDIntPair implements Serializable {

    private UUID first;
    private Integer second;

    public H2OUUIDIntPair() {
    }

    public H2OUUIDIntPair(UUID first, Integer second) {
        this.first = first;
        this.second = second;
    }

    public UUID getFirst() {
        return first;
    }

    public void setFirst(UUID first) {
        this.first = first;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
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

        H2OUUIDIntPair that = (H2OUUIDIntPair) o;

        if (first != null ? !first.equals(that.first) : that.first != null) return false;
        return second != null ? second.equals(that.second) : that.second == null;

    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}
