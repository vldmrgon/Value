package com.shared.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class PurchaseDTO {

    @Setter
    @Getter
    @Builder
    public static class Request {
        @NotNull
        private ShopperDTO.Request shopper;
        @NotNull
        private ProductDTO.Request product;
    }

    @Setter
    @Getter
    @Builder
    public static class Response {
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