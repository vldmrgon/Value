package com.my.commerce.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import com.my.commerce.service.PurchaseStatisticService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PurchaseStatisticController {

    private final PurchaseStatisticService purchaseStatisticService;

    @GetMapping("/statistic/products")
    public ResponseEntity<List<Object>> getProductsByEmailShopper(@RequestParam String email) {
        List<Object> productByEmailShopper = purchaseStatisticService.getProductByEmailShopper(email);
        return ResponseEntity.status(HttpStatus.OK).body(productByEmailShopper);
    }

    @GetMapping("/statistic/shoppers")
    public ResponseEntity<List<Object>> getShoppersByModelProduct(@RequestParam String model) {
        List<Object> shopperByModelProduct = purchaseStatisticService.getShopperByModelProduct(model);
        return ResponseEntity.status(HttpStatus.OK).body(shopperByModelProduct);
    }
}