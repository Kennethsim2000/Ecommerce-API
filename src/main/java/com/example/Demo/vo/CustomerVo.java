package com.example.Demo.vo;

import java.util.Set;

import com.example.Demo.model.Order;

import lombok.Data;

@Data
public class CustomerVo {

    public Long customerId;
    private String name;
    private String address;
    private Set<Long> orderIds;
}
