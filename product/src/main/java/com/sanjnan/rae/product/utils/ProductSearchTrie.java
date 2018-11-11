package com.sanjnan.rae.product.utils;

import com.sanjnan.rae.common.exception.ServiceNotAvailableException;
import com.sanjnan.rae.common.pojos.Product;
import com.sanjnan.rae.common.utils.SanjnanMessages;
import com.sanjnan.rae.product.couchbase.ProductRepository;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductSearchTrie {

  private static Trie<String, Product> trie;
  public static ProductSearchTrie INSTANCE = new ProductSearchTrie();

  public void rebuild(ProductRepository productRepository) {

    Map<String, Product> library = new LinkedHashMap<>();
    for (Product product : productRepository.findAll()) {
      library.put(product.getName() + " " + product.getDescription() + " " + product.getSpecification() + " " + product.getEan(), product);
    }
    trie = new PatriciaTrie<>(library);
  }
  public Set<Product> search(String substring) {

    if (trie == null) {
      throw new ServiceNotAvailableException(SanjnanMessages.PRODUCT_NOT_INDEXED);
    }
    return trie.prefixMap(substring).values().stream().collect(Collectors.toSet());
  }
}
