package com.sanjnan.rae.identityserver.pojos;

import com.couchbase.client.java.repository.annotation.Field;
import org.joda.time.DateTime;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by vinay on 1/28/16.
 */
@Document
public class Tenant extends Base {
  public Tenant() {
  }

  public Tenant(UUID createdBy, UUID updatedBy, String name, String email, String discriminator) {
    super(UUID.randomUUID(), DateTime.now(), DateTime.now(), createdBy, updatedBy, name);
    this.email = email;
    this.discriminator = discriminator;
    this.computeRegions = new ArrayList<>();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDiscriminator() {
    return discriminator;
  }

  public void setDiscriminator(String discriminator) {
    this.discriminator = discriminator;
  }

  public List<UUID> getComputeRegions() {
    return computeRegions;
  }

  public void setComputeRegions(List<UUID> computeRegions) {
    this.computeRegions = computeRegions;
  }

  @Override
  public String toString() {
    return "Tenant{" +
        "email='" + email + '\'' +
        ", discriminator='" + discriminator + '\'' +
        ", computeRegions=" + computeRegions +
        "} " + super.toString();
  }

  @Field
  private String email;
  @Field
  private String discriminator;
  @Field
  private List<UUID> computeRegions;
}
