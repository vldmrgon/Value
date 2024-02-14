package com.my.pro.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.pro.service.ShopperService;
import com.shared.dto.ShopperDTO;

import org.mockito.ArgumentMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.my.pro.objects.Mocks;

@SpringBootTest
@AutoConfigureMockMvc
public class ShopperControllerTest {

    private static final String GET_SHOPPER_BY_EMAIL = "/api/v1/shopper/get/{shopperEmail}";
    private static final String POST_CREATE_SHOPPER = "/api/v1/shopper/create";

    @MockBean
    private ShopperService shopperService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void addShopperWithValidRequestControllerTest() throws Exception {
        Mockito
                .when(shopperService.addShopper(ArgumentMatchers.any(ShopperDTO.Request.class)))
                .thenReturn(Mocks.RESPONSE_SHOPPER_DTO);

        mockMvc
                .perform(MockMvcRequestBuilders.post(POST_CREATE_SHOPPER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Mocks.RESPONSE_SHOPPER_DTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Mocks.ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(Mocks.SHOPPER_FIRST_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(Mocks.SHOPPER_LAST_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(Mocks.SHOPPER_EMAIL));
    }

    @Test
    void addShopperWithInvalidRequestControllerTest() throws Exception {
        ShopperDTO.Request request = ShopperDTO.Request.builder()
                .email("invalid-email")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post(POST_CREATE_SHOPPER)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void getShopperControllerTest() throws Exception {
        Mockito
                .when(shopperService.getShopperByEmail(Mocks.SHOPPER_EMAIL))
                .thenReturn(Mocks.RESPONSE_SHOPPER_DTO);

        mockMvc
                .perform(MockMvcRequestBuilders.get(GET_SHOPPER_BY_EMAIL, Mocks.SHOPPER_EMAIL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Mocks.ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(Mocks.SHOPPER_FIRST_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(Mocks.SHOPPER_LAST_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(Mocks.SHOPPER_EMAIL));
    }
}