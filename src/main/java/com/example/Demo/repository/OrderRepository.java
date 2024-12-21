package com.example.Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.Demo.model.Order;

import jakarta.transaction.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // takes in productId and delete all orders that have this productId

    //Native query refers to actual sql queries (referring to actual database objects). These queries are the sql statements which can be directly
    // executed in database using a database client.
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM orders ord WHERE ord.product_id = :productId", nativeQuery = true)
    int deleteOrderByProductId(Long productId);

}

//The Strengths of JPQL
//Database Agnosticism: JPQL queries are not tied to any specific database syntax, offering portability across different database systems.
//Object-Oriented: Queries are written against the entity model, allowing for more intuitive development in object-oriented programming languages.
//Simplification: JPQL abstracts complex joins and other SQL operations into simpler, more readable queries.