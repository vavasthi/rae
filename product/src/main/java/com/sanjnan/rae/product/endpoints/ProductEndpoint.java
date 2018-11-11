/*
 * Copyright 2016 (c) Hubble Connected (HKT) Ltd. - All Rights Reserved
 *
 * Proprietary and confidential.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 */

package com.sanjnan.rae.product.endpoints;

import com.sanjnan.rae.common.constants.SanjnanConstants;
import com.sanjnan.rae.common.endpoints.v1.BaseEndpoint;
import com.sanjnan.rae.common.pojos.Product;
import com.sanjnan.rae.product.service.ProductService;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by vinay on 1/4/16.
 */
@RestController
@RequestMapping(SanjnanConstants.V1_PRODUCT_ENDPOINT)
@Api(value="Traditional product endpoint", description="This endpoint provides Product lifecycle operations")
public class ProductEndpoint extends BaseEndpoint {

  Logger logger = Logger.getLogger(ProductEndpoint.class);

  @Autowired
  private ProductService productService;

  @Transactional(readOnly = true)
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public Optional<Product> getProduct() throws IOException {

    Product product = new Product();
    return Optional.of(product);
  }

  @Transactional(readOnly = true)
  @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @PostAuthorize(SanjnanConstants.ANNOTATION_ROLE_USER)
  public Optional<Product> getProduct(@PathVariable("id") UUID id) throws IOException {

    Optional<Product> optionalProduct = productService.getProduct(id);
    return optionalProduct;
  }

  @Transactional
  @PreAuthorize(SanjnanConstants.ANNOTATION_ROLE_ADMIN_OR_SUPERADMIN)
  @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  public Product createProduct(@RequestBody @Valid Product product) {
    return productService.createProduct(product);
  }

  @Transactional
  @PreAuthorize(SanjnanConstants.ANNOTATION_ROLE_USER_ADMIN_AND_TENANT_ADMIN)
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
  public Product updateProduct(@PathVariable("id") UUID id,
                               @RequestBody @Valid Product product) throws InvocationTargetException, IllegalAccessException {
    return productService.updateProduct(id, product);
  }

  @Transactional
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
  @PreAuthorize(SanjnanConstants.ANNOTATION_ROLE_SUPERADMIN)
  public Product deleteProduct(@PathVariable("id") UUID id) throws InvalidParameterSpecException {

    return productService.deleteProduct(id);
  }

}
