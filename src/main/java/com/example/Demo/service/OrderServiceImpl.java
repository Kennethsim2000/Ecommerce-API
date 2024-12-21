package com.example.Demo.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Demo.exception.CustomerNotFoundException;
import com.example.Demo.exception.OrderNotFoundException;
import com.example.Demo.exception.ProductNotFoundException;
import com.example.Demo.model.Customer;
import com.example.Demo.model.Order;
import com.example.Demo.model.Product;
import com.example.Demo.repository.CustomerRepository;
import com.example.Demo.repository.OrderRepository;
import com.example.Demo.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    @Override
    public Order addOrder(Long customerId, Long productId) throws CustomerNotFoundException, ProductNotFoundException {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Invalid customer id provided."));
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Invalid product id provided"));
        Order order = new Order();
        order.setProductId(product);
        order.setCustomerId(customer);
        orderRepository.save(order);
        Set<Order> orders = customer.getOrder();
        orders.add(order);
        customerRepository.save(customer);
        Customer testCustomer = customerRepository.findById(customerId).orElse(null);
        return order;
    }

    @Transactional
    @Override
    public Order deleteOrder(Long orderId) throws OrderNotFoundException {
        Order deletedOrder = orderRepository.findById(orderId).orElseThrow(()->new OrderNotFoundException("Invalid order id provided"));
        orderRepository.deleteById(orderId);
        return deletedOrder;

    }
}
