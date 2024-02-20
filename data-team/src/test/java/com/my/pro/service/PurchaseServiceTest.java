package com.my.pro.service;

import com.my.pro.util.converter.PurchaseConverter;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;

import com.my.pro.repository.PurchaseRepository;
import com.my.pro.repository.ProductRepository;
import com.my.pro.repository.ShopperRepository;

import com.my.pro.domain.entity.Purchase;
import com.my.pro.domain.entity.Product;
import com.my.pro.domain.entity.Shopper;

import com.shared.dto.PurchaseDTO;
import com.shared.dto.ProductDTO;
import com.shared.dto.ShopperDTO;

import com.my.pro.objects.Mocks;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private ShopperRepository shopperRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private PurchaseConverter purchaseConverter;
    @InjectMocks
    private PurchaseService purchaseService;
    @Mock
    private RedisService redisService;

    @Test
    public void testAddPurchase() {
        ShopperDTO.Request shopperRequest = Mocks.REQUEST_SHOPPER_DTO;
        ProductDTO.Request productRequest = Mocks.REQUEST_PRODUCT_DTO;

        Purchase purchaseEntity = Mocks.PURCHASE;
        Shopper shopperEntity = Mocks.SHOPPER;
        Product productEntity = Mocks.PRODUCT;

        Mockito.
                when(shopperRepository.findByEmail(Mocks.SHOPPER_EMAIL))
                .thenReturn(Optional.of(shopperEntity));

        Mockito.
                when(productRepository.findByModel(Mocks.PRODUCT_MODEL))
                .thenReturn(Optional.of(productEntity));

        Mockito.
                when(purchaseRepository.save(ArgumentMatchers.any()))
                .thenReturn(purchaseEntity);

        PurchaseDTO.Response responseDTO = Mocks.RESPONSE_PURCHASE_DTO;

        Mockito
                .when(purchaseConverter.mapEntityToResponse(purchaseEntity))
                .thenReturn(responseDTO);

        PurchaseDTO.Response result = purchaseService.addPurchase(shopperRequest, productRequest);

        Assertions.assertEquals(Mocks.ID, result.getId());
        Assertions.assertEquals(Mocks.SHOPPER_EMAIL, result.getShopper().getEmail());
        Assertions.assertEquals(Mocks.PRODUCT_MODEL, result.getProduct().getModel());

        Mockito.verify(shopperRepository, Mockito.times(1)).findByEmail(Mocks.SHOPPER_EMAIL);
        Mockito.verify(productRepository, Mockito.times(1)).findByModel(Mocks.PRODUCT_MODEL);
        Mockito.verify(purchaseRepository, Mockito.times(1)).save(ArgumentMatchers.any());
        Mockito.verify(purchaseConverter, Mockito.times(1)).mapEntityToResponse(purchaseEntity);
        Mockito.verify(redisService, Mockito.times(1)).setProductByEmailShopper(Mocks.SHOPPER_EMAIL, result.getProduct());
        Mockito.verify(redisService, Mockito.times(1)).setShopperByModelProduct(Mocks.PRODUCT_MODEL, result.getShopper());
    }
}