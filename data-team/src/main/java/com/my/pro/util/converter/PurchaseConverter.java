package com.my.pro.util.converter;

import org.springframework.stereotype.Component;

import com.my.pro.domain.entity.Purchase;

import lombok.RequiredArgsConstructor;

import com.shared.dto.PurchaseDTO;

@Component
@RequiredArgsConstructor
public class PurchaseConverter implements EntityMapping<PurchaseDTO.Request, Purchase, PurchaseDTO.Response> {

    private final ProductConverter productConverter;
    private final ShopperConverter shopperConverter;

    @Override
    public Purchase mapRequestToEntity(PurchaseDTO.Request request) {
        return Purchase.builder()
                .product(productConverter.mapRequestToEntity(request.getProduct()))
                .shopper(shopperConverter.mapRequestToEntity(request.getShopper()))
                .build();
    }

    @Override
    public PurchaseDTO.Response mapEntityToResponse(Purchase entity) {
        return PurchaseDTO.Response.builder()
                .id(entity.getId())
                .purchaseDate(entity.getPurchaseDate())
                .product(productConverter.mapEntityToResponse(entity.getProduct()))
                .shopper(shopperConverter.mapEntityToResponse(entity.getShopper()))
                .build();
    }
}