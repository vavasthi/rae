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

  public String getUpc() {
    return upc;
  }

  public String getSpecification() {
    return specification;
  }

  public void setSpecification(String specification) {
    this.specification = specification;
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

  private String brandId;
  private String description;
  private String specification;
  private String ean;
  private String upc;
  private String code;
}
