package com.example.Demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public ResponseEntity<CommonResult<List<ProductVo>>> getAllProducts() {
        List<Product> lst = productService.listAllProducts();
        List<ProductVo> res = new ArrayList<>();
        for(Product p: lst) {
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(p, productVo);
            res.add(productVo);
        }
        CommonResult<List<ProductVo>> result = CommonResult.success(res, "Successfully retrieved all products");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommonResult<ProductVo>> addProduct(@RequestBody ProductDto productDto) {
        Product newProduct = new Product();
        BeanUtils.copyProperties(productDto, newProduct);
        Product product = productService.saveProduct(newProduct);
        ProductVo productVo = new ProductVo();
        BeanUtils.copyProperties(product, productVo);
        CommonResult<ProductVo> res = CommonResult.success(productVo, "Product successfully added");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    //to be added
    @DeleteMapping
    public CommonResult<ProductVo> deleteProduct(@RequestParam Long productId) {
        ProductVo productVo = new ProductVo();
        //Before we delete, we need to make sure that we delete all orders referencing this productId
        Product deletedProduct = productService.deleteById(productId);
        BeanUtils.copyProperties(deletedProduct, productVo);
        CommonResult<ProductVo> res = CommonResult.success(productVo, "Product successfully deleted");
        return res;
    }

}
