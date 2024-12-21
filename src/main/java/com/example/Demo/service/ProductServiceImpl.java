package com.example.Demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Demo.exception.ProductNotFoundException;
import com.example.Demo.model.Product;
import com.example.Demo.repository.OrderRepository;
import com.example.Demo.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Transactional
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Transactional
    @Override
    public Product deleteById(Long productId) throws ProductNotFoundException {
        Integer deleteOrders = orderRepository.deleteOrderByProductId(productId);
        Product deletedProduct = productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Invalid productId provided"));;
        productRepository.deleteById(productId);
        return deletedProduct;
    }

    @Override
    public Product findById(Long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(()->new ProductNotFoundException("Invalid productId provided"));
        return product;
    }
}
