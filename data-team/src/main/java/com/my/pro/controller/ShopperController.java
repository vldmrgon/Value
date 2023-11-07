package com.my.pro.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.my.pro.service.ShopperService;
import lombok.RequiredArgsConstructor;
import com.shared.dto.ShopperDTO;
import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ShopperController {

    private final ShopperService shopperService;

    @PostMapping("/shopper/create")
    public ResponseEntity<ShopperDTO.Response> addShopper(@RequestBody @Valid ShopperDTO.Request request) {
        ShopperDTO.Response response = shopperService.addShopper(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/shopper/get/{shopperEmail}")
    public ResponseEntity<ShopperDTO.Response> getShopper(@PathVariable String shopperEmail) {
        ShopperDTO.Response response = shopperService.getShopperByEmail(shopperEmail);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}