package com.my.pro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.shared.dto.ProductDTO;
import com.shared.dto.ShopperDTO;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void setProductByEmailShopper(String email, ProductDTO.Response product) {
        String key = "productsByShopper:" + email;
        redisTemplate.opsForValue().set(key, product);
    }

    public void setShopperByModelProduct(String model, ShopperDTO.Response shopper) {
        String key = "shoppersByProduct:" + model;
        redisTemplate.opsForValue().set(key, shopper);
    }
}