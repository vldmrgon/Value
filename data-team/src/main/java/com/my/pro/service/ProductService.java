package com.my.pro.service;

import com.my.pro.exception.ProductBusinessException;
import com.my.pro.exception.ValidationException;

import com.my.pro.util.converter.ProductConverter;

import com.my.pro.repository.ProductRepository;

import org.springframework.stereotype.Service;

import com.my.pro.domain.entity.Product;

import lombok.RequiredArgsConstructor;

import com.shared.dto.ProductDTO;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductDTO.Response addProduct(ProductDTO.Request productDto) {
        Product product = productConverter.mapRequestToEntity(productDto);
        validateProduct(product);
        Product shopperEntity = productRepository.save(product);
        return productConverter.mapEntityToResponse(shopperEntity);
    }

    public ProductDTO.Response getProductByModel(String productModel) {
        Product productEntity = productRepository.findByModel(productModel)
                .orElseThrow(() -> new ProductBusinessException("The product with the model: " + productModel + " not found"));
        return productConverter.mapEntityToResponse(productEntity);
    }

    private void validateProduct(Product product) {
        String model = product.getModel();
        if (productRepository.existsByModel(model)) {
            throw new ValidationException("The product with the model: " + model + " already exists");
        }
    }
}