package com.my.commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class PurchaseStatisticService {

    private static final String PREFIX_EMAIL_SHOPPER_REDIS_COLLECTION = "productsByShopper:";
    private static final String PREFIX_MODEL_PRODUCT_REDIS_COLLECTION = "shoppersByProduct:";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    private ListOperations<String, Object> listOperations;

    @PostConstruct
    public void initListOps() {
        listOperations = redisTemplate.opsForList();
    }

    public List<Object> getProductByEmailShopper(String emailShopper) {
        return listOperations.range(PREFIX_EMAIL_SHOPPER_REDIS_COLLECTION + emailShopper, 0, -1);
    }

    public List<Object> getShopperByModelProduct(String modelProduct) {
        return listOperations.range(PREFIX_MODEL_PRODUCT_REDIS_COLLECTION + modelProduct, 0, -1);
    }
}