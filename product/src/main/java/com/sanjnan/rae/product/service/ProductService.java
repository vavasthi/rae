package com.sanjnan.rae.product.service;

import com.sanjnan.rae.common.exception.EntityAlreadyExistsException;
import com.sanjnan.rae.common.exception.EntityNotFoundException;
import com.sanjnan.rae.common.pojos.Account;
import com.sanjnan.rae.common.pojos.Product;
import com.sanjnan.rae.common.utils.ObjectPatcher;
import com.sanjnan.rae.common.utils.SanjnanMessages;
import com.sanjnan.rae.product.couchbase.ProductRepository;
import com.sanjnan.rae.product.utils.ProductSearchTrie;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class ProductService {
  
  @Autowired
  private ProductRepository productRepository;
  
  Logger logger = Logger.getLogger(ProductService.class);

  public Optional<Product> getProduct(UUID id) {

      Optional<Product> productOptional = productRepository.findById(id.toString());
      if (productOptional.isPresent()) {
        return productOptional;
      }
    throw new EntityNotFoundException(String.format(SanjnanMessages.PRODUCT_NOT_FOUND, id.toString()));
  }

  public Product createProduct(Product product) {
    if (product.getEan() != null) {
      
      if (productRepository.findByEan(product.getEan()).isPresent()) {
        throw new EntityAlreadyExistsException(String.format(SanjnanMessages.PRODUCT_ALREADY_EXISTS_FOR_EAN, product.getEan()));
      }
    }
    if (product.getUpc() != null) {

      if (productRepository.findByUpc(product.getUpc()).isPresent()) {
        throw new EntityAlreadyExistsException(String.format(SanjnanMessages.PRODUCT_ALREADY_EXISTS_FOR_UPC, product.getUpc()));
      }
    }
    if (product.getId() != null) {
      if (productRepository.findById(product.getId()).isPresent()) {
        throw new EntityAlreadyExistsException(String.format(SanjnanMessages.PRODUCT_ALREADY_EXISTS_FOR_ID, product.getId()));
      }
    }
    product.setId(UUID.randomUUID().toString());
    product = productRepository.save(product);
    return product;
  }
  public Product updateProduct(UUID id, Product product) throws InvocationTargetException, IllegalAccessException {

    Optional<Product> productOptional = productRepository.findById(id.toString());
    if (productOptional.isPresent()) {
      Product storedProduct = productOptional.get();
      ObjectPatcher.diffAndPatch(Product.class, storedProduct, Product.class, product);
      storedProduct = productRepository.save(storedProduct);
      return storedProduct;
    }
    throw new EntityNotFoundException(String.format(SanjnanMessages.PRODUCT_NOT_FOUND, product.getId()));
  }
  public Product deleteProduct(UUID id) {
    Optional<Product> ProductOptional = productRepository.findById(id.toString());
    if (ProductOptional.isPresent()) {
      Product storedProduct = ProductOptional.get();
      productRepository.deleteById(id.toString());
      return storedProduct;
    }
    throw new EntityNotFoundException(String.format(SanjnanMessages.PRODUCT_NOT_FOUND, id.toString()));
  }

  public void searchRebuild() {

    ProductSearchTrie.INSTANCE.rebuild(productRepository);
  }
  public Set<Product> search(String substring) {

    return ProductSearchTrie.INSTANCE.search(substring);
  }
}
