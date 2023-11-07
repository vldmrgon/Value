package com.my.pro.util.converter;

import org.springframework.stereotype.Component;
import com.my.pro.domain.entity.Shopper;
import com.shared.dto.ShopperDTO;

@Component
public class ShopperConverter implements EntityMapping<ShopperDTO.Request, Shopper, ShopperDTO.Response> {

    @Override
    public Shopper mapRequestToEntity(ShopperDTO.Request request) {
        return Shopper.builder()
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
    }

    @Override
    public ShopperDTO.Response mapEntityToResponse(Shopper entity) {
        return ShopperDTO.Response.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }
}