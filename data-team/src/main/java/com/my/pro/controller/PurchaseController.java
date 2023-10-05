package com.my.pro.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.my.pro.service.PurchaseService;

import lombok.RequiredArgsConstructor;

import com.shared.dto.PurchaseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping("/purchase/create")
    public ResponseEntity<PurchaseDTO.Response> createPurchase(@RequestBody PurchaseDTO.Request request) {
        PurchaseDTO.Response response = purchaseService.addPurchase(request.getShopper(), request.getProduct());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}