package com.example.Demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.Demo.config.CommonResult;
import com.example.Demo.vo.CustomerVo;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CommonResult<CustomerVo>> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        CommonResult<CustomerVo> res = CommonResult.fail(ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CommonResult<CustomerVo>> handleProductNotFoundException(ProductNotFoundException ex) {
        CommonResult<CustomerVo> res = CommonResult.fail(ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CommonResult<CustomerVo>> handleOrderNotFoundException(OrderNotFoundException ex) {
        CommonResult<CustomerVo> res = CommonResult.fail(ex.getMessage());
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
}
