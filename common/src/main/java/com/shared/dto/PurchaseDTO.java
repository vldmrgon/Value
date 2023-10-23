package com.shared.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@RedisHash("Purchase")
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