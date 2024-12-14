package com.example.Demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Demo.config.CommonResult;
import com.example.Demo.dto.ProductDto;
import com.example.Demo.model.Product;
import com.example.Demo.service.ProductService;
import com.example.Demo.vo.ProductVo;

@RestController // restController helps to return json/xml in the response body
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<CommonResult<ProductVo>> getProduct(@RequestParam Long productId) {
        Product product = productService.findById(productId);
        if(product == null) {
            CommonResult<ProductVo> res = CommonResult.fail("Product not found");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        ProductVo productVo = new ProductVo();
        BeanUtils.copyProperties(product, productVo);
        CommonResult<ProductVo> res =  CommonResult.success(productVo, "Product successfully found");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping
    @RequestMapping("/all")
    public CommonResult<List<ProductVo>> getAllProducts() {
        List<Product> lst = productService.listAllProducts();
        List<ProductVo> res = new ArrayList<>();
        for(Product p: lst) {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(p, productVo);
            res.add(productVo);
        }
        return CommonResult.success(res, "Successfully retrieved all products");
    }

    @PostMapping
    public CommonResult<ProductVo> addProduct(@RequestBody ProductDto productDto) {
        Product newProduct = new Product();
        BeanUtils.copyProperties(productDto, newProduct);
        System.out.println(newProduct);
        Product product = productService.saveProduct(newProduct);
        ProductVo productVo = new ProductVo();
        BeanUtils.copyProperties(product, productVo);
        return CommonResult.success(productVo, "Product successfully added");

    }

}
