package com.example.Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Demo.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
