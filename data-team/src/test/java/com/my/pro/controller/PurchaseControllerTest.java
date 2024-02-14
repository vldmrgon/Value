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
import com.my.pro.service.PurchaseService;

import org.mockito.ArgumentMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.shared.dto.ProductDTO;
import com.shared.dto.ShopperDTO;

import com.my.pro.objects.Mocks;

@SpringBootTest
@AutoConfigureMockMvc
class PurchaseControllerTest {

    private static final String POST_CREATE_PURCHASE = "/api/v1/purchase/create";

    @MockBean
    private PurchaseService purchaseService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createPurchaseControllerTest() throws Exception {
        Mockito.
                when(purchaseService.addPurchase(
                        ArgumentMatchers.any(ShopperDTO.Request.class),
                        ArgumentMatchers.any(ProductDTO.Request.class)))
                .thenReturn(Mocks.RESPONSE_PURCHASE_DTO);

        mockMvc
                .perform(MockMvcRequestBuilders.post(POST_CREATE_PURCHASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Mocks.REQUEST_PURCHASE_DTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Mocks.ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.purchaseDate").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.shopper.firstName").value(Mocks.SHOPPER_FIRST_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shopper.lastName").value(Mocks.SHOPPER_LAST_NAME))
                .andExpect(MockMvcResultMatchers.jsonPath("$.shopper.email").value(Mocks.SHOPPER_EMAIL))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product.model").value(Mocks.PRODUCT_MODEL))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product.category").value(Mocks.PRODUCT_CATEGORY))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product.brand").value(Mocks.PRODUCT_BRAND));
    }
}