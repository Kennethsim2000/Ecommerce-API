package com.example.Demo.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Demo.config.CommonResult;
import com.example.Demo.dto.CustomerDto;
import com.example.Demo.model.Customer;
import com.example.Demo.model.Order;
import com.example.Demo.service.CustomerService;
import com.example.Demo.vo.CustomerVo;

@RestController // RestController automatically serializes response body into json/xml
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping
    public ResponseEntity<CommonResult<CustomerVo>> getCustomer(@RequestParam Long customerId) {
        Customer customer = service.findById(customerId);
        if(customer == null) {
            CommonResult<CustomerVo> res = CommonResult.fail("Customer does not exist");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
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
    public CommonResult<CustomerVo> addCustomer(@RequestBody CustomerDto customerDto) {
        Customer newCustomer = new Customer();
        BeanUtils.copyProperties(customerDto, newCustomer);
        Customer addedCustomer = service.saveCustomer(newCustomer);
        CustomerVo customerVo = new CustomerVo();
        BeanUtils.copyProperties(addedCustomer, customerVo);
        return CommonResult.success(customerVo, "Customer successfully added");
    }

    @PutMapping
    public CommonResult<CustomerVo> editCustomer(@RequestBody CustomerDto customerDto) {
        Customer editedCustomer = new Customer();
        BeanUtils.copyProperties(customerDto, editedCustomer);
        Customer customer = service.updateCustomer(editedCustomer);
        CustomerVo customerVo = new CustomerVo();
        BeanUtils.copyProperties(customer, customerVo);
        return CommonResult.success(customerVo, "Customer successfully edited");
    }

    //To be completed
    @DeleteMapping
    public CommonResult<CustomerVo> deleteCustomer(@RequestParam Long customerId) {
        CustomerVo customerVo = new CustomerVo();
        CommonResult<CustomerVo> res =  CommonResult.success(customerVo, "Customer successfully deleted");
        return res;
    }
}
