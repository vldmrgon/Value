package com.my.pro.repository;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

import com.my.pro.domain.entity.Shopper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@DataMongoTest
public class ShopperRepositoryTest {

    @Autowired
    private ShopperRepository shopperRepository;
    private Shopper shopper;

    @BeforeEach
    void setUp() {
        shopper = Shopper.builder().email("test@example.com").build();
    }

    @AfterEach
    void clean() {
        shopperRepository.deleteAll();
    }

    @Test
    void shouldSaveShopper() {
        var savedShopper = shopperRepository.save(shopper);
        assertThat(savedShopper).isNotNull();
    }

    @Test
    void shouldGetShopper() {
        shopperRepository.save(shopper);
        var savedShopper = shopperRepository.findById(shopper.getId()).get();
        assertThat(savedShopper.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void shouldUpdateShopper() {
        shopperRepository.save(shopper);

        var savedShopper = shopperRepository.findById(shopper.getId()).get();
        savedShopper.setEmail("updated@example.com");

        shopperRepository.save(savedShopper);

        var updatedShopper = shopperRepository.findById(shopper.getId()).get();

        assertThat(updatedShopper.getEmail()).isEqualTo("updated@example.com");
    }

    @Test
    void shouldDeleteShopper() {
        shopperRepository.save(shopper);
        shopperRepository.delete(shopper);
        assertThat(shopperRepository.findAll()).isEmpty();
    }
}
