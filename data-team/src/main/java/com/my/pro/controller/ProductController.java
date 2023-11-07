package com.my.pro.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.my.pro.service.ProductService;
import lombok.RequiredArgsConstructor;
import com.shared.dto.ProductDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product/create")
    public ResponseEntity<ProductDTO.Response> createProduct(@RequestBody ProductDTO.Request request) {
        ProductDTO.Response response = productService.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/product/get/{productModel}")
    public ResponseEntity<ProductDTO.Response> getProduct(@PathVariable String productModel) {
        ProductDTO.Response productByModel = productService.getProductByModel(productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productByModel);
    }
}