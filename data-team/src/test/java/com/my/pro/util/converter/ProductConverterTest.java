package com.my.pro.util.converter;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.my.pro.domain.entity.Product;
import com.shared.dto.ProductDTO;

import com.my.pro.objects.Mocks;

@ExtendWith(MockitoExtension.class)
public class ProductConverterTest {

    @InjectMocks
    private ProductConverter productConverter;

    @Test
    public void testMapRequestToEntity() {
        ProductDTO.Request request = Mocks.REQUEST_PRODUCT_DTO;
        Product productEntity = productConverter.mapRequestToEntity(request);

        Assertions.assertEquals(request.getModel(), productEntity.getModel());
        Assertions.assertEquals(request.getCategory(), productEntity.getCategory());
        Assertions.assertEquals(request.getBrand(), productEntity.getBrand());
    }

    @Test
    public void testMapEntityToResponse() {
        Product productEntity = Mocks.PRODUCT;
        ProductDTO.Response responseDTO = productConverter.mapEntityToResponse(productEntity);

        Assertions.assertEquals(productEntity.getId(), responseDTO.getId());
        Assertions.assertEquals(productEntity.getModel(), responseDTO.getModel());
        Assertions.assertEquals(productEntity.getCategory(), responseDTO.getCategory());
        Assertions.assertEquals(productEntity.getBrand(), responseDTO.getBrand());
    }
}