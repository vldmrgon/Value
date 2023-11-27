package com.my.pro.service;

import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.my.pro.util.converter.ShopperConverter;
import com.my.pro.repository.ShopperRepository;
import com.my.pro.domain.entity.Shopper;

import com.shared.dto.ShopperDTO;

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
        ShopperDTO.Request request = ShopperDTO.Request.builder()
                .email("test@email.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        Shopper shopperEntity = Shopper.builder()
                .email("test@email.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        Mockito
                .when(shopperConverter.mapRequestToEntity(request))
                .thenReturn(shopperEntity);
        Mockito
                .when(shopperRepository.existsByEmail("test@email.com"))
                .thenReturn(false);
        Mockito
                .when(shopperRepository.save(shopperEntity))
                .thenReturn(shopperEntity);
        Mockito
                .when(shopperConverter.mapEntityToResponse(shopperEntity))
                .thenReturn(ShopperDTO.Response.builder()
                        .id("123")
                        .email("test@email.com")
                        .firstName("John")
                        .lastName("Doe")
                        .build()
                );

        ShopperDTO.Response response = shopperService.addShopper(request);

        Assertions.assertEquals("test@email.com", response.getEmail());
        Assertions.assertEquals("John", response.getFirstName());
        Assertions.assertEquals("Doe", response.getLastName());

        Mockito.verify(shopperConverter, Mockito.times(1)).mapRequestToEntity(request);
        Mockito.verify(shopperRepository, Mockito.times(1)).existsByEmail("test@email.com");
        Mockito.verify(shopperRepository, Mockito.times(1)).save(shopperEntity);
        Mockito.verify(shopperConverter, Mockito.times(1)).mapEntityToResponse(shopperEntity);
    }

    @Test
    public void testGetShopperByEmail() {
        String email = "test@email.com";
        Shopper shopperEntity = Shopper.builder()
                .id("123")
                .email("test@email.com")
                .firstName("John")
                .lastName("Doe")
                .build();

        Mockito
                .when(shopperRepository.findByEmail(email))
                .thenReturn(Optional.of(shopperEntity));

        Mockito
                .when(shopperConverter.mapEntityToResponse(shopperEntity))
                .thenReturn(ShopperDTO.Response.builder()
                        .id("123")
                        .email("test@email.com")
                        .firstName("John")
                        .lastName("Doe")
                        .build()
                );

        ShopperDTO.Response response = shopperService.getShopperByEmail(email);

        Assertions.assertEquals("test@email.com", response.getEmail());
        Assertions.assertEquals("John", response.getFirstName());
        Assertions.assertEquals("Doe", response.getLastName());

        Mockito.verify(shopperRepository, Mockito.times(1)).findByEmail(email);
        Mockito.verify(shopperConverter, Mockito.times(1)).mapEntityToResponse(shopperEntity);
    }
}