package com.example.Demo.service;

import java.util.List;

import com.example.Demo.model.Product;

public interface ProductService {
    Product saveProduct(Product product);
    List<Product> listAllProducts();
    Product deleteById(Long productId);
    Product findById(Long productId);
}
