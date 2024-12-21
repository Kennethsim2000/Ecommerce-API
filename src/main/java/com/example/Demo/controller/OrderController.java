package com.example.Demo.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Demo.config.CommonResult;
import com.example.Demo.dto.OrderDto;
import com.example.Demo.exception.CustomerNotFoundException;
import com.example.Demo.model.Order;
import com.example.Demo.service.OrderService;
import com.example.Demo.vo.OrderVo;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public CommonResult<OrderVo> addOrder(@RequestBody OrderDto orderDto) {
        Order order = new Order();
        order = orderService.addOrder(orderDto.getCustomerId(), orderDto.getProductId());
        OrderVo orderVo = new OrderVo();
        BeanUtils.copyProperties(order, orderVo);
        orderVo.setCustomerId(order.getCustomerId().getCustomerId());
        orderVo.setProductId(order.getProductId().getProductId());
        return CommonResult.success(orderVo, "Order created");
    }

    @DeleteMapping
    public CommonResult<OrderVo> deleteOrder(@RequestParam Long orderId) {
        OrderVo orderVo = new OrderVo();
        Order deletedOrder = orderService.deleteOrder(orderId);
        BeanUtils.copyProperties(deletedOrder, orderVo);
        orderVo.setCustomerId(deletedOrder.getCustomerId().getCustomerId());
        orderVo.setProductId(deletedOrder.getProductId().getProductId());
        return CommonResult.success(orderVo, "Order deleted");
    }


}
