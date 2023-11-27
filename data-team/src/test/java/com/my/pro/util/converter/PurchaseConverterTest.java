package com.my.pro.util.converter;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;

import com.my.pro.domain.entity.Purchase;
import com.my.pro.domain.entity.Product;
import com.my.pro.domain.entity.Shopper;

import com.shared.dto.PurchaseDTO;
import com.shared.dto.ProductDTO;
import com.shared.dto.ShopperDTO;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class PurchaseConverterTest {

    @InjectMocks
    private PurchaseConverter purchaseConverter;

    @Mock
    private ProductConverter productConverter;

    @Mock
    private ShopperConverter shopperConverter;

    @Test
    public void testMapRequestToEntity() {
        PurchaseDTO.Request request = PurchaseDTO.Request.builder()
                .product(ProductDTO.Request.builder()
                        .model("Laptop")
                        .category("Electronics")
                        .brand("BrandX")
                        .build())
                .shopper(ShopperDTO.Request.builder()
                        .email("test@email.com")
                        .firstName("John")
                        .lastName("Doe")
                        .build())
                .build();

        Mockito
                .when(productConverter.mapRequestToEntity(request.getProduct()))
                .thenReturn(new Product("456", "Laptop", "Electronics", "BrandX"));

        Mockito
                .when(shopperConverter.mapRequestToEntity(request.getShopper()))
                .thenReturn(new Shopper("789", "test@email.com", "John", "Doe"));

        Purchase purchaseEntity = purchaseConverter.mapRequestToEntity(request);

        Assertions.assertEquals("456", purchaseEntity.getProduct().getId());
        Assertions.assertEquals("789", purchaseEntity.getShopper().getId());
    }

    @Test
    public void testMapEntityToResponse() {
        Purchase purchaseEntity = Purchase.builder()
                .id("987")
                .purchaseDate(new Date())
                .product(Product.builder()
                        .id("123")
                        .model("Laptop")
                        .category("Electronics")
                        .brand("BrandX")
                        .build())
                .shopper(Shopper.builder()
                        .id("456")
                        .email("test@email.com")
                        .firstName("John")
                        .lastName("Doe")
                        .build())
                .build();

        Mockito
                .when(productConverter.mapEntityToResponse(purchaseEntity.getProduct()))
                .thenReturn(new ProductDTO.Response("123", "Laptop", "Electronics", "BrandX"));

        Mockito
                .when(shopperConverter.mapEntityToResponse(purchaseEntity.getShopper()))
                .thenReturn(new ShopperDTO.Response("456", "test@email.com", "John", "Doe"));

        PurchaseDTO.Response responseDTO = purchaseConverter.mapEntityToResponse(purchaseEntity);

        Assertions.assertEquals("987", responseDTO.getId());
        Assertions.assertEquals(purchaseEntity.getPurchaseDate(), responseDTO.getPurchaseDate());
        Assertions.assertEquals("123", responseDTO.getProduct().getId());
        Assertions.assertEquals("456", responseDTO.getShopper().getId());
    }
}