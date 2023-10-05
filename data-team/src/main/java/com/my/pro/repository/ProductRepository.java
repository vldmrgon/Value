package com.my.pro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.my.pro.domain.entity.Product;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findByModel(String model);

    boolean existsByModel(String model);
}
