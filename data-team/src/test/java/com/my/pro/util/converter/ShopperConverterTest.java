package com.my.pro.util.converter;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;

import com.my.pro.domain.entity.Shopper;
import com.shared.dto.ShopperDTO;

import com.my.pro.objects.Mocks;

@ExtendWith(MockitoExtension.class)
public class ShopperConverterTest {

    @InjectMocks
    private ShopperConverter shopperConverter;

    @Test
    public void testMapRequestToEntity() {
        ShopperDTO.Request request = Mocks.REQUEST_SHOPPER_DTO;
        Shopper shopperEntity = shopperConverter.mapRequestToEntity(request);

        Assertions.assertEquals(Mocks.SHOPPER_EMAIL, shopperEntity.getEmail());
        Assertions.assertEquals(Mocks.SHOPPER_FIRST_NAME, shopperEntity.getFirstName());
        Assertions.assertEquals(Mocks.SHOPPER_LAST_NAME, shopperEntity.getLastName());
    }

    @Test
    public void testMapEntityToResponse() {
        Shopper shopperEntity = Mocks.SHOPPER;
        ShopperDTO.Response responseDTO = shopperConverter.mapEntityToResponse(shopperEntity);

        Assertions.assertEquals(Mocks.ID, responseDTO.getId());
        Assertions.assertEquals(Mocks.SHOPPER_EMAIL, responseDTO.getEmail());
        Assertions.assertEquals(Mocks.SHOPPER_FIRST_NAME, responseDTO.getFirstName());
        Assertions.assertEquals(Mocks.SHOPPER_LAST_NAME, responseDTO.getLastName());
    }
}