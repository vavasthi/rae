/*
 * Copyright (c) 2018 Sanjnan Knowledge Technology Private Limited
 *
 * All Rights Reserved
 * This file contains software code that is proprietary and confidential.
 *  Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 *  Author: vavasthi
 */

package com.sanjnan.rae.identityserver.pojos;

import com.couchbase.client.java.repository.annotation.Field;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sanjnan.rae.common.annotations.H2ONonNullString;
import com.sanjnan.rae.common.annotations.SkipPatching;
import com.sanjnan.rae.identityserver.serializers.H2ODateTimeDeserializer;
import com.sanjnan.rae.identityserver.serializers.H2ODateTimeSerializer;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Created by vinay on 2/10/16.
 */
@JsonInclude(Include.NON_NULL)
public class Base implements Serializable {
  public Base(String id, DateTime createdAt, DateTime updatedAt, String createdBy, String updatedBy, String name) {
    this.id = id;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.createdBy = createdBy;
    this.updatedBy = updatedBy;
    this.name = name;
  }

  public Base() {
    this.id = UUID.randomUUID().toString();
    this.createdAt = DateTime.now();
    this.updatedAt = DateTime.now();
  }

  public Base(String name) {
    this.name = name;
  }

  @SkipPatching
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public DateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(DateTime createdAt) {
    this.createdAt = createdAt;
  }

  public DateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(DateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    final Base base = (Base) o;

    if (id != null ? !id.equals(base.id) : base.id != null) return false;
    return name != null ? name.equals(base.name) : base.name == null;

  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Base{" +
        "id=" + id +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        ", createdBy='" + createdBy + '\'' +
        ", updatedBy='" + updatedBy + '\'' +
        ", name='" + name + '\'' +
        '}';
  }

  @Id
  private String id;
  @JsonSerialize(using = H2ODateTimeSerializer.class)
  @JsonDeserialize(using = H2ODateTimeDeserializer.class)
  @Field
  private DateTime createdAt;
  @JsonSerialize(using = H2ODateTimeSerializer.class)
  @JsonDeserialize(using = H2ODateTimeDeserializer.class)
  @Field
  private DateTime updatedAt;
  @Field
  private String createdBy;
  @Field
  private String updatedBy;
  private @H2ONonNullString(min = 5, max = 255, nullAllowed = false) String name;
}
