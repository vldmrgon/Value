package com.my.pro.domain.entity;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void testProductBuilder() {
        Product product = Product.builder()
                .id("123")
                .model("Laptop")
                .category("Electronics")
                .brand("BrandX")
                .build();

        assertThat(product.getId()).isEqualTo("123");
        assertThat(product.getModel()).isEqualTo("Laptop");
        assertThat(product.getCategory()).isEqualTo("Electronics");
        assertThat(product.getBrand()).isEqualTo("BrandX");
    }

    @Test
    public void testProductAllArgsConstructor() {
        Product product = new Product("123", "Laptop", "Electronics", "BrandX");

        assertThat(product.getId()).isEqualTo("123");
        assertThat(product.getModel()).isEqualTo("Laptop");
        assertThat(product.getCategory()).isEqualTo("Electronics");
        assertThat(product.getBrand()).isEqualTo("BrandX");
    }
}