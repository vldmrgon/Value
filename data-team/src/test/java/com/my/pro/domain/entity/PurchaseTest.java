package com.my.pro.domain.entity;


import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import java.util.Date;

public class PurchaseTest {

    @Test
    public void testPurchaseBuilder() {
        Shopper shopper = Shopper.builder()
                .id("1")
                .email("test@email.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        Product product = Product.builder()
                .id("2")
                .model("Laptop")
                .category("Electronics")
                .brand("BrandX")
                .build();

        Purchase purchase = Purchase.builder()
                .id("123")
                .purchaseDate(new Date())
                .shopper(shopper)
                .product(product)
                .build();

        assertThat(purchase.getId()).isEqualTo("123");
        assertThat(purchase.getPurchaseDate()).isNotNull();
        assertThat(purchase.getShopper()).isEqualTo(shopper);
        assertThat(purchase.getProduct()).isEqualTo(product);
    }

    @Test
    public void testPurchaseAllArgsConstructor() {
        Shopper shopper = Shopper.builder()
                .id("1")
                .email("test@email.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        Product product = Product.builder()
                .id("2")
                .model("Laptop")
                .category("Electronics")
                .brand("BrandX")
                .build();

        Date purchaseDate = new Date();

        Purchase purchase = new Purchase("123", purchaseDate, shopper, product);

        assertThat(purchase.getId()).isEqualTo("123");
        assertThat(purchase.getPurchaseDate()).isEqualTo(purchaseDate);
        assertThat(purchase.getShopper()).isEqualTo(shopper);
        assertThat(purchase.getProduct()).isEqualTo(product);
    }
}