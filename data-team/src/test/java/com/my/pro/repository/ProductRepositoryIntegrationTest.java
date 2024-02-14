package com.my.pro.repository;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import com.my.pro.domain.entity.Product;
import com.my.pro.objects.Mocks;
import org.junit.jupiter.api.*;

@DataMongoTest
public class ProductRepositoryIntegrationTest {

    @Autowired
    private ProductRepository productRepository;
    private Product product;

    @BeforeEach
    void setUp() {
        product = Mocks.PRODUCT;
    }

    @AfterEach
    void clean() {
        productRepository.deleteAll();
    }

    @Test
    void shouldSaveProduct() {
        var savedProduct = productRepository.save(product);
        assertThat(savedProduct).isNotNull();
    }

    @Test
    void shouldGetProduct() {
        productRepository.save(product);
        var savedProduct = productRepository.findById(product.getId()).get();
        assertThat(savedProduct.getCategory()).isEqualTo(Mocks.PRODUCT_CATEGORY);
        assertThat(savedProduct.getBrand()).isEqualTo(Mocks.PRODUCT_BRAND);
    }

    @Test
    void shouldUpdateProduct() {
        productRepository.save(product);

        var savedProduct = productRepository.findById(product.getId()).get();
        savedProduct.setCategory("UpdatedCategory");
        savedProduct.setBrand("UpdatedBrand");

        productRepository.save(savedProduct);

        var updatedProduct = productRepository.findById(product.getId()).get();

        assertThat(updatedProduct.getCategory()).isEqualTo("UpdatedCategory");
        assertThat(updatedProduct.getBrand()).isEqualTo("UpdatedBrand");
    }

    @Test
    void shouldDeleteProduct() {
        productRepository.save(product);
        productRepository.deleteAll();
        assertThat(productRepository.findAll()).isEmpty();
    }
}