package com.example.Demo.vo;

import java.time.LocalDateTime;

import com.example.Demo.model.Customer;
import com.example.Demo.model.Product;

import lombok.Data;

@Data
public class OrderVo {

    public Long orderId;

    private Long customerId;

    private LocalDateTime dateCreated;

    private Long productId;

}
