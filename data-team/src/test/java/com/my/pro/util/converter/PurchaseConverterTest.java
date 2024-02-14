package com.my.pro.util.converter;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;

import com.my.pro.domain.entity.Purchase;
import com.shared.dto.PurchaseDTO;

import com.my.pro.objects.Mocks;

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
        PurchaseDTO.Request request = Mocks.REQUEST_PURCHASE_DTO;

        Mockito
                .when(productConverter.mapRequestToEntity(request.getProduct()))
                .thenReturn(Mocks.PRODUCT);

        Mockito
                .when(shopperConverter.mapRequestToEntity(request.getShopper()))
                .thenReturn(Mocks.SHOPPER);

        Purchase purchaseEntity = purchaseConverter.mapRequestToEntity(request);

        Assertions.assertEquals(Mocks.ID, purchaseEntity.getProduct().getId());
        Assertions.assertEquals(Mocks.ID, purchaseEntity.getShopper().getId());
    }

    @Test
    public void testMapEntityToResponse() {
        Purchase purchaseEntity = Mocks.PURCHASE;

        Mockito
                .when(productConverter.mapEntityToResponse(purchaseEntity.getProduct()))
                .thenReturn(Mocks.RESPONSE_PRODUCT_DTO);

        Mockito
                .when(shopperConverter.mapEntityToResponse(purchaseEntity.getShopper()))
                .thenReturn(Mocks.RESPONSE_SHOPPER_DTO);

        PurchaseDTO.Response responseDTO = purchaseConverter.mapEntityToResponse(purchaseEntity);

        Assertions.assertEquals(Mocks.ID, responseDTO.getId());
        Assertions.assertEquals(purchaseEntity.getPurchaseDate(), responseDTO.getPurchaseDate());
        Assertions.assertEquals(Mocks.ID, responseDTO.getProduct().getId());
        Assertions.assertEquals(Mocks.ID, responseDTO.getShopper().getId());
    }
}