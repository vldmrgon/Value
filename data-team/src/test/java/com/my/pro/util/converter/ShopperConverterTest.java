package com.my.pro.util.converter;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.my.pro.domain.entity.Shopper;
import com.shared.dto.ShopperDTO;

@ExtendWith(MockitoExtension.class)
public class ShopperConverterTest {

    @InjectMocks
    private ShopperConverter shopperConverter;

    @Test
    public void testMapRequestToEntity() {
        ShopperDTO.Request request = new ShopperDTO.Request("test@email.com", "John", "Doe");
        Shopper shopperEntity = shopperConverter.mapRequestToEntity(request);

        Assertions.assertEquals(request.getEmail(), shopperEntity.getEmail());
        Assertions.assertEquals(request.getFirstName(), shopperEntity.getFirstName());
        Assertions.assertEquals(request.getLastName(), shopperEntity.getLastName());
    }

    @Test
    public void testMapEntityToResponse() {
        Shopper shopperEntity = new Shopper("1", "test@email.com", "John", "Doe");
        ShopperDTO.Response responseDTO = shopperConverter.mapEntityToResponse(shopperEntity);

        Assertions.assertEquals(shopperEntity.getId(), responseDTO.getId());
        Assertions.assertEquals(shopperEntity.getEmail(), responseDTO.getEmail());
        Assertions.assertEquals(shopperEntity.getFirstName(), responseDTO.getFirstName());
        Assertions.assertEquals(shopperEntity.getLastName(), responseDTO.getLastName());
    }
}