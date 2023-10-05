package com.my.pro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.my.pro.domain.entity.Purchase;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {
}
