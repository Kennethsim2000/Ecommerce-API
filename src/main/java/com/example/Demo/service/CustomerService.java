package com.example.Demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Demo.model.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> findAllCustomers();
    Customer deleteById(Long customerId);
    Customer findById(Long customerId);
    Customer updateCustomer(Customer customer);
}
