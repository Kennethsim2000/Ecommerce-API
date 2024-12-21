package com.example.Demo.controller;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Demo.config.CommonResult;
import com.example.Demo.dto.CustomerDto;
import com.example.Demo.exception.CustomerNotFoundException;
import com.example.Demo.model.Customer;
import com.example.Demo.model.Order;
import com.example.Demo.service.CustomerService;
import com.example.Demo.service.OrderService;
import com.example.Demo.vo.CustomerVo;

@RestController // RestController automatically serializes response body into json/xml
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<CommonResult<CustomerVo>> getCustomer(@RequestParam Long customerId) {
        Customer customer = customerService.findById(customerId);
        CustomerVo customerVo = new CustomerVo();
        BeanUtils.copyProperties(customer, customerVo);
        Set<Order> orders = customer.getOrder();
        Set<Long> orderIds = new HashSet<>();
        for(Order order: orders) {
            orderIds.add(order.getOrderId());
        }
        customerVo.setOrderIds(orderIds);
        CommonResult<CustomerVo> res = CommonResult.success(customerVo, "Customer successfully retrieved");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommonResult<CustomerVo>> addCustomer(@RequestBody CustomerDto customerDto) {
        Customer newCustomer = new Customer();
        BeanUtils.copyProperties(customerDto, newCustomer);
        Customer addedCustomer = customerService.saveCustomer(newCustomer);
        CustomerVo customerVo = new CustomerVo();
        BeanUtils.copyProperties(addedCustomer, customerVo);
        CommonResult<CustomerVo> res = CommonResult.success(customerVo, "Customer successfully added");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<CommonResult<Long>> deleteCustomer(@RequestParam Long customerId) {
        Customer customer = customerService.findById(customerId);
        Set<Order> customerOrders = customer.getOrder();
        //since orders have a foreign key relation to customer, we need to make sure to delete all orders referring to this customer first
        for(Order order: customerOrders) {
            orderService.deleteOrder(order.getOrderId());
        }
        Customer deletedCustomer = customerService.deleteById(customerId);
        CustomerVo customerVo = new CustomerVo();
        CommonResult<Long> res =  CommonResult.success(customerId, "Customer successfully deleted");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/orders")
    public ResponseEntity<CommonResult<Set<Order>>> getOrders(@RequestParam Long customerId) {
        Customer customer = customerService.findById(customerId);
        Set<Order> customerOrders = customer.getOrder();
        CommonResult<Set<Order>> res =  CommonResult.success(customerOrders, "Orders successfully retrieved");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
