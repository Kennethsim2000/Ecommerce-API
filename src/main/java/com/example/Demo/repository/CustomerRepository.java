package com.example.Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {


}
