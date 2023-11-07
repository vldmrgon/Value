package com.my.pro.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import com.my.pro.exception.PurchaseBusinessException;
import com.my.pro.util.converter.PurchaseConverter;

import com.my.pro.repository.PurchaseRepository;
import com.my.pro.repository.ProductRepository;
import com.my.pro.repository.ShopperRepository;

import com.my.pro.domain.entity.Purchase;
import com.my.pro.domain.entity.Product;
import com.my.pro.domain.entity.Shopper;

import lombok.RequiredArgsConstructor;

import com.shared.dto.PurchaseDTO;
import com.shared.dto.ProductDTO;
import com.shared.dto.ShopperDTO;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ShopperRepository shopperRepository;
    private final ProductRepository productRepository;
    private final PurchaseConverter purchaseConverter;
    private final RedisService redisService;

    @Transactional
    public PurchaseDTO.Response addPurchase(ShopperDTO.Request shopper, ProductDTO.Request product) {

        String email = shopper.getEmail();
        String model = product.getModel();

        Shopper shopperEntityFindByEmail = shopperRepository.findByEmail(email)
                .orElseThrow(() -> new PurchaseBusinessException("The shopper with the email: " + email + " not found"));

        Product productEntityFindByModel = productRepository.findByModel(model)
                .orElseThrow(() -> new PurchaseBusinessException("The product with the model: " + model + " not found"));

        Purchase purchase = Purchase.builder()
                .purchaseDate(new Date())
                .shopper(shopperEntityFindByEmail)
                .product(productEntityFindByModel)
                .build();

        Purchase purchaseEntity = purchaseRepository.save(purchase);

        PurchaseDTO.Response response = purchaseConverter.mapEntityToResponse(purchaseEntity);

        redisService.setProductByEmailShopper(response.getShopper().getEmail(), response.getProduct());
        redisService.setShopperByModelProduct(response.getProduct().getModel(), response.getShopper());

        return response;
    }
}