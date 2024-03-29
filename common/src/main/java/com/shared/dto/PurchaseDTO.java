package com.shared.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class PurchaseDTO {

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        @NotNull
        private ShopperDTO.Request shopper;
        @NotNull
        private ProductDTO.Request product;
    }

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response implements Serializable {
        @NotNull
        private String id;
        @NotNull
        @NotBlank
        private Date purchaseDate;
        @NotNull
        private ShopperDTO.Response shopper;
        @NotNull
        private ProductDTO.Response product;
    }
}