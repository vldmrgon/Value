package com.my.pro.repository;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import com.my.pro.domain.entity.Product;
import com.my.pro.domain.entity.Purchase;
import com.my.pro.domain.entity.Shopper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.my.pro.objects.Mocks;

import java.util.Date;

@DataMongoTest
public class PurchaseRepositoryIntegrationTest {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ShopperRepository shopperRepository;

    private Purchase purchase;

    @BeforeEach
    void setUp() {
        Product savedProduct = productRepository.save(Mocks.PRODUCT);
        Shopper savedShopper = shopperRepository.save(Mocks.SHOPPER);

        purchase = Purchase.builder()
                .purchaseDate(new Date())
                .product(savedProduct)
                .shopper(savedShopper)
                .build();
    }

    @AfterEach
    void clean() {
        purchaseRepository.deleteAll();
    }

    @Test
    void shouldSavePurchase() {
        var savedPurchase = purchaseRepository.save(purchase);
        assertThat(savedPurchase).isNotNull();
    }

    @Test
    void shouldGetPurchase() {
        purchaseRepository.save(purchase);
        var savedPurchase = purchaseRepository.findAll().get(0);
        assertThat(savedPurchase.getShopper().getEmail()).isEqualTo(Mocks.SHOPPER_EMAIL);
        assertThat(savedPurchase.getProduct().getCategory()).isEqualTo(Mocks.PRODUCT_CATEGORY);
        assertThat(savedPurchase.getProduct().getBrand()).isEqualTo(Mocks.PRODUCT_BRAND);
    }
}