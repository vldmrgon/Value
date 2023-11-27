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

import java.util.Optional;
import java.util.Date;

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
        ShopperDTO.Request shopperRequest = ShopperDTO.Request.builder()
                .email("test@email.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        ProductDTO.Request productRequest = ProductDTO.Request.builder()
                .model("Laptop")
                .category("Electronics")
                .brand("BrandX")
                .build();

        Shopper shopperEntity = Shopper.builder()
                .id("123")
                .email("test@email.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        Product productEntity = Product.builder()
                .id("456")
                .model("Laptop")
                .category("Electronics")
                .brand("BrandX")
                .build();

        Mockito.
                when(shopperRepository.findByEmail("test@email.com"))
                .thenReturn(Optional.of(shopperEntity));
        Mockito.
                when(productRepository.findByModel("Laptop"))
                .thenReturn(Optional.of(productEntity));

        Purchase purchaseEntity = Purchase.builder()
                .id("789")
                .purchaseDate(new Date())
                .shopper(shopperEntity)
                .product(productEntity)
                .build();

        Mockito.
                when(purchaseRepository.save(ArgumentMatchers.any()))
                .thenReturn(purchaseEntity);

        PurchaseDTO.Response responseDTO = PurchaseDTO.Response.builder()
                .id("789")
                .purchaseDate(new Date())
                .shopper(ShopperDTO.Response.builder()
                        .id("123")
                        .email("test@email.com")
                        .firstName("John")
                        .lastName("Doe")
                        .build()
                )
                .product(ProductDTO.Response.builder()
                        .id("456")
                        .model("Laptop")
                        .category("Electronics")
                        .brand("BrandX")
                        .build()
                )
                .build();

        Mockito
                .when(purchaseConverter.mapEntityToResponse(purchaseEntity))
                .thenReturn(responseDTO);

        PurchaseDTO.Response result = purchaseService.addPurchase(shopperRequest, productRequest);

        Assertions.assertEquals("789", result.getId());
        Assertions.assertEquals("test@email.com", result.getShopper().getEmail());
        Assertions.assertEquals("Laptop", result.getProduct().getModel());

        Mockito.verify(shopperRepository, Mockito.times(1)).findByEmail("test@email.com");
        Mockito.verify(productRepository, Mockito.times(1)).findByModel("Laptop");
        Mockito.verify(purchaseRepository, Mockito.times(1)).save(ArgumentMatchers.any());
        Mockito.verify(purchaseConverter, Mockito.times(1)).mapEntityToResponse(purchaseEntity);
        Mockito.verify(redisService, Mockito.times(1)).setProductByEmailShopper("test@email.com", result.getProduct());
        Mockito.verify(redisService, Mockito.times(1)).setShopperByModelProduct("Laptop", result.getShopper());
    }
}