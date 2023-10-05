package com.my.pro.util.converter;

import org.springframework.stereotype.Component;

import com.my.pro.domain.entity.Product;

import com.shared.dto.ProductDTO;

@Component
public class ProductConverter implements EntityMapping<ProductDTO.Request, Product, ProductDTO.Response> {

    @Override
    public Product mapRequestToEntity(ProductDTO.Request request) {
        return Product.builder()
                .model(request.getModel())
                .category(request.getCategory())
                .brand(request.getBrand())
                .build();
    }

    @Override
    public ProductDTO.Response mapEntityToResponse(Product entity) {
        return ProductDTO.Response.builder()
                .id(entity.getId())
                .model(entity.getModel())
                .category(entity.getCategory())
                .brand(entity.getBrand())
                .build();
    }
}