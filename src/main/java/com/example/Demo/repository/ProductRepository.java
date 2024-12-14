package com.example.Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Demo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
