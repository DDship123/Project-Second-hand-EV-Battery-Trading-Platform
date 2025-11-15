package org.example.be.service;

import org.example.be.entity.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    Product updateProduct(Integer id, Product productDetails);
    void deleteProduct(Integer id);
    Product save(Product product);
}