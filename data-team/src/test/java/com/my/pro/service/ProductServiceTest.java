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
        ProductDTO.Request request = ProductDTO.Request.builder()
                .model("Laptop")
                .category("Electronics")
                .brand("BrandX")
                .build();

        Product productEntity = Product.builder()
                .model("Laptop")
                .category("Electronics")
                .brand("BrandX")
                .build();

        Mockito
                .when(productConverter.mapRequestToEntity(request))
                .thenReturn(productEntity);

        Mockito
                .when(productRepository.existsByModel("Laptop"))
                .thenReturn(false);
        Mockito
                .when(productRepository.save(productEntity))
                .thenReturn(productEntity);

        Mockito
                .when(productConverter.mapEntityToResponse(productEntity))
                .thenReturn(ProductDTO.Response.builder()
                        .id("123")
                        .model("Laptop")
                        .category("Electronics")
                        .brand("BrandX")
                        .build()
                );

        ProductDTO.Response response = productService.addProduct(request);

        Assertions.assertEquals("Laptop", response.getModel());
        Assertions.assertEquals("Electronics", response.getCategory());
        Assertions.assertEquals("BrandX", response.getBrand());

        Mockito.verify(productConverter, Mockito.times(1)).mapRequestToEntity(request);
        Mockito.verify(productRepository, Mockito.times(1)).existsByModel("Laptop");
        Mockito.verify(productRepository, Mockito.times(1)).save(productEntity);
        Mockito.verify(productConverter, Mockito.times(1)).mapEntityToResponse(productEntity);
    }

    @Test
    public void testGetProductByModel() {
        String model = "Laptop";
        Product productEntity = Product.builder()
                .id("123")
                .model("Laptop")
                .category("Electronics")
                .brand("BrandX")
                .build();

        Mockito
                .when(productRepository.findByModel(model))
                .thenReturn(Optional.of(productEntity));
        Mockito
                .when(productConverter.mapEntityToResponse(productEntity))
                .thenReturn(ProductDTO.Response.builder()
                        .id("123")
                        .model("Laptop")
                        .category("Electronics")
                        .brand("BrandX")
                        .build()
                );

        ProductDTO.Response response = productService.getProductByModel(model);

        Assertions.assertEquals("Laptop", response.getModel());
        Assertions.assertEquals("Electronics", response.getCategory());
        Assertions.assertEquals("BrandX", response.getBrand());

        Mockito.verify(productRepository, Mockito.times(1)).findByModel(model);
        Mockito.verify(productConverter, Mockito.times(1)).mapEntityToResponse(productEntity);
    }
}