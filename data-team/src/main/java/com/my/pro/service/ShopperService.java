package com.my.pro.service;

import com.shared.exception.ValidationBusinessException;
import com.shared.exception.ShopperBusinessException;

import com.my.pro.util.converter.ShopperConverter;
import com.my.pro.repository.ShopperRepository;
import org.springframework.stereotype.Service;

import com.my.pro.domain.entity.Shopper;
import com.shared.dto.ShopperDTO;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class ShopperService {

    private final ShopperRepository shopperRepository;
    private final ShopperConverter shopperConverter;

    public ShopperDTO.Response addShopper(ShopperDTO.Request shopperDto) {
        Shopper shopper = shopperConverter.mapRequestToEntity(shopperDto);
        validateShopper(shopper);
        Shopper shopperEntity = shopperRepository.save(shopper);
        return shopperConverter.mapEntityToResponse(shopperEntity);
    }

    @SneakyThrows
    public ShopperDTO.Response getShopperByEmail(String shopperEmail) {
        Shopper shopperEntity = shopperRepository.findByEmail(shopperEmail)
                .orElseThrow(() -> new ShopperBusinessException("The shopper with the email: " + shopperEmail + " not found"));
        return shopperConverter.mapEntityToResponse(shopperEntity);
    }

    private void validateShopper(Shopper shopper) {
        String email = shopper.getEmail();
        if (shopperRepository.existsByEmail(email)) {
            throw new ValidationBusinessException("The shopper with email: " + email + " already exists");
        }
    }
}