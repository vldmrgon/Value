package com.my.pro.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Mock;

import com.my.pro.util.converter.ProductConverter;
import com.my.pro.repository.ProductRepository;
import com.my.pro.domain.entity.Product;

import com.shared.dto.ProductDTO;
import com.my.pro.objects.Mocks;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductConverter productConverter;
    @InjectMocks
    private ProductService productService;

    @Test
    public void testAddProduct() {
        ProductDTO.Request request = Mocks.REQUEST_PRODUCT_DTO;
        Product productEntity = Mocks.PRODUCT;

        Mockito
                .when(productConverter.mapRequestToEntity(request))
                .thenReturn(productEntity);

        Mockito
                .when(productRepository.existsByModel(Mocks.PRODUCT_MODEL))
                .thenReturn(false);

        Mockito
                .when(productRepository.save(productEntity))
                .thenReturn(productEntity);

        Mockito
                .when(productConverter.mapEntityToResponse(productEntity))
                .thenReturn(Mocks.RESPONSE_PRODUCT_DTO);

        ProductDTO.Response response = productService.addProduct(request);

        Assertions.assertEquals(Mocks.PRODUCT_MODEL, response.getModel());
        Assertions.assertEquals(Mocks.PRODUCT_CATEGORY, response.getCategory());
        Assertions.assertEquals(Mocks.PRODUCT_BRAND, response.getBrand());

        Mockito.verify(productConverter, Mockito.times(1)).mapRequestToEntity(request);
        Mockito.verify(productRepository, Mockito.times(1)).existsByModel(Mocks.PRODUCT_MODEL);
        Mockito.verify(productRepository, Mockito.times(1)).save(productEntity);
        Mockito.verify(productConverter, Mockito.times(1)).mapEntityToResponse(productEntity);
    }

    @Test
    public void testGetProductByModel() {
        Product productEntity = Mocks.PRODUCT;
        String model = Mocks.PRODUCT_MODEL;

        Mockito
                .when(productRepository.findByModel(model))
                .thenReturn(Optional.of(productEntity));

        Mockito
                .when(productConverter.mapEntityToResponse(productEntity))
                .thenReturn(Mocks.RESPONSE_PRODUCT_DTO);

        ProductDTO.Response response = productService.getProductByModel(model);

        Assertions.assertEquals(Mocks.PRODUCT_MODEL, response.getModel());
        Assertions.assertEquals(Mocks.PRODUCT_CATEGORY, response.getCategory());
        Assertions.assertEquals(Mocks.PRODUCT_BRAND, response.getBrand());

        Mockito.verify(productRepository, Mockito.times(1)).findByModel(model);
        Mockito.verify(productConverter, Mockito.times(1)).mapEntityToResponse(productEntity);
    }
}