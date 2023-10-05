package com.my.pro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.my.pro.domain.entity.Shopper;

import java.util.Optional;

public interface ShopperRepository extends MongoRepository<Shopper, String> {

    Optional<Shopper> findByEmail(String email);

    boolean existsByEmail(String email);
}
