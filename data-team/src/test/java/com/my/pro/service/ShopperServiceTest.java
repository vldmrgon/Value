package com.my.pro.service;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.my.pro.util.converter.ShopperConverter;
import com.my.pro.repository.ShopperRepository;
import com.my.pro.domain.entity.Shopper;

import com.shared.dto.ShopperDTO;
import com.my.pro.objects.Mocks;

import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ShopperServiceTest {

    @Mock
    private ShopperRepository shopperRepository;
    @Mock
    private ShopperConverter shopperConverter;
    @InjectMocks
    private ShopperService shopperService;

    @Test
    public void testAddShopper() {
        ShopperDTO.Request request = Mocks.REQUEST_SHOPPER_DTO;
        String shopperEmail = Mocks.SHOPPER_EMAIL;
        Shopper shopperEntity = Mocks.SHOPPER;

        Mockito
                .when(shopperConverter.mapRequestToEntity(request))
                .thenReturn(shopperEntity);

        Mockito
                .when(shopperRepository.existsByEmail(shopperEmail))
                .thenReturn(false);

        Mockito
                .when(shopperRepository.save(shopperEntity))
                .thenReturn(shopperEntity);

        Mockito
                .when(shopperConverter.mapEntityToResponse(shopperEntity))
                .thenReturn(Mocks.RESPONSE_SHOPPER_DTO);

        ShopperDTO.Response response = shopperService.addShopper(request);

        Assertions.assertEquals(Mocks.SHOPPER_EMAIL, response.getEmail());
        Assertions.assertEquals(Mocks.SHOPPER_FIRST_NAME, response.getFirstName());
        Assertions.assertEquals(Mocks.SHOPPER_LAST_NAME, response.getLastName());

        Mockito.verify(shopperConverter, Mockito.times(1)).mapRequestToEntity(request);
        Mockito.verify(shopperRepository, Mockito.times(1)).existsByEmail(shopperEmail);
        Mockito.verify(shopperRepository, Mockito.times(1)).save(shopperEntity);
        Mockito.verify(shopperConverter, Mockito.times(1)).mapEntityToResponse(shopperEntity);
    }

    @Test
    public void testGetShopperByEmail() {
        String shopperEmail = Mocks.SHOPPER_EMAIL;
        Shopper shopperEntity = Mocks.SHOPPER;

        Mockito
                .when(shopperRepository.findByEmail(shopperEmail))
                .thenReturn(Optional.of(shopperEntity));

        Mockito
                .when(shopperConverter.mapEntityToResponse(shopperEntity))
                .thenReturn(Mocks.RESPONSE_SHOPPER_DTO);

        ShopperDTO.Response response = shopperService.getShopperByEmail(shopperEmail);

        Assertions.assertEquals(Mocks.SHOPPER_EMAIL, response.getEmail());
        Assertions.assertEquals(Mocks.SHOPPER_FIRST_NAME, response.getFirstName());
        Assertions.assertEquals(Mocks.SHOPPER_LAST_NAME, response.getLastName());

        Mockito.verify(shopperRepository, Mockito.times(1)).findByEmail(shopperEmail);
        Mockito.verify(shopperConverter, Mockito.times(1)).mapEntityToResponse(shopperEntity);
    }
}