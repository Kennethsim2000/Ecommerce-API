package com.example.Demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Demo.model.Product;
import com.example.Demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository repository;

    @Override
    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> listAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product deleteById(Long productId) {
        Optional<Product> deletedProduct = repository.findById(productId);
        if(deletedProduct.isEmpty()) {
            return null;
        }
        repository.deleteById(productId);
        return deletedProduct.get();
    }

    @Override
    public Product findById(Long productId) {
        Optional<Product> product = repository.findById(productId);
        return product.orElse(null);
    }
}
