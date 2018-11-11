package com.sanjnan.rae.common.pojos;

import com.sanjnan.rae.common.annotations.SkipPatching;
import org.joda.time.DateTime;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.*;

/**
 * Created by vinay on 1/28/16.
 */
@Document
public class Product extends Base {
  public Product() {
    super(UUID.randomUUID().toString(), DateTime.now(), DateTime.now(),null, null, "");
    setCreatedBy(getId());
    setCreatedBy(getId());
  }

  public Product(String name, String name1, String brandId, String description, String ean) {
    super(UUID.randomUUID().toString(), DateTime.now(), DateTime.now(),null, null, "");
    this.name = name1;
    this.brandId = brandId;
    this.description = description;
    this.ean = ean;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  public String getBrandId() {
    return brandId;
  }

  public void setBrandId(String brandId) {
    this.brandId = brandId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getEan() {
    return ean;
  }

  public void setEan(String ean) {
    this.ean = ean;
  }

  public String getSpecification() {
    return specification;
  }

  public void setSpecification(String specification) {
    this.specification = specification;
  }

  public String getUpc() {
    return upc;
  }

  public void setUpc(String upc) {
    this.upc = upc;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  private String name;
  private String brandId;
  private String description;
  private String specification;
  private String ean;
  private String upc;
  private String code;
}
