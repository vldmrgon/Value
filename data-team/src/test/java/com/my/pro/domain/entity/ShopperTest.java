package com.my.pro.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class ShopperTest {

    @Test
    public void testShopperBuilder() {
        Shopper shopper = Shopper.builder()
                .id("123")
                .email("test@email.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        assertThat(shopper.getId()).isEqualTo("123");
        assertThat(shopper.getEmail()).isEqualTo("test@email.com");
        assertThat(shopper.getFirstName()).isEqualTo("John");
        assertThat(shopper.getLastName()).isEqualTo("Doe");
    }

    @Test
    public void testShopperAllArgsConstructor() {
        Shopper shopper = new Shopper("123", "test@email.com", "John", "Doe");

        assertThat(shopper.getId()).isEqualTo("123");
        assertThat(shopper.getEmail()).isEqualTo("test@email.com");
        assertThat(shopper.getFirstName()).isEqualTo("John");
        assertThat(shopper.getLastName()).isEqualTo("Doe");
    }
}