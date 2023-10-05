package com.shared.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ProductDTO {

    @Setter
    @Getter
    @Builder
    public static class Request {
        @NotNull
        @NotBlank
        private String model;
        @NotNull
        @NotBlank
        private String category;
        @NotNull
        @NotBlank
        private String brand;
    }

    @Setter
    @Getter
    @Builder
    public static class Response {
        @NotNull
        private String id;
        @NotNull
        @NotBlank
        private String model;
        @NotNull
        @NotBlank
        private String category;
        @NotNull
        @NotBlank
        private String brand;
    }
}