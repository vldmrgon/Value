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
import com.my.pro.service.ProductService;
import com.shared.dto.ProductDTO;

import org.mockito.ArgumentMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.my.pro.objects.Mocks;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    private static final String GET_PRODUCT_BY_MODEL = "/api/v1/product/get/{productModel}";
    private static final String POST_CREATE_PRODUCT = "/api/v1/product/create";

    @MockBean
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void createProductControllerTest() throws Exception {
        Mockito.
                when(productService.addProduct(ArgumentMatchers.any(ProductDTO.Request.class)))
                .thenReturn(Mocks.RESPONSE_PRODUCT_DTO);

        mockMvc
                .perform(MockMvcRequestBuilders.post(POST_CREATE_PRODUCT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(Mocks.REQUEST_PRODUCT_DTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Mocks.ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value(Mocks.PRODUCT_MODEL))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(Mocks.PRODUCT_CATEGORY))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(Mocks.PRODUCT_BRAND));
    }

    @Test
    void getProductControllerTest() throws Exception {
        Mockito
                .when(productService.getProductByModel(Mocks.PRODUCT_MODEL))
                .thenReturn(Mocks.RESPONSE_PRODUCT_DTO);

        mockMvc
                .perform(MockMvcRequestBuilders.get(GET_PRODUCT_BY_MODEL, Mocks.PRODUCT_MODEL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Mocks.ID))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value(Mocks.PRODUCT_MODEL))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(Mocks.PRODUCT_CATEGORY))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value(Mocks.PRODUCT_BRAND));
    }
}