package com.my.pro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import lombok.Getter;

import com.shared.dto.ProductDTO;
import com.shared.dto.ShopperDTO;

@Log4j2
@Service
public class RedisService {

    @Getter
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private ListOperations<String, Object> listOperations;

    @PostConstruct
    public void initListOps() {
        listOperations = redisTemplate.opsForList();
    }

    public void setProductByEmailShopper(String email, ProductDTO.Response product) {
        String key = "productsByShopper:" + email;
        Long aLong = listOperations.rightPush(key, product);
        log.debug("The product ID: " + product.getId() + " was added");
    }

    public void setShopperByModelProduct(String model, ShopperDTO.Response shopper) {
        String key = "shoppersByProduct:" + model;
        Long aLong = listOperations.rightPush(key, shopper);
        log.debug("The shopper ID: " + shopper.getId() + " was added");
    }
}