package com.example.Demo.service;

import com.example.Demo.model.Order;

public interface OrderService {
    Order addOrder(Long customerId, Long productId);
    Order deleteOrder(Long orderId);
}
