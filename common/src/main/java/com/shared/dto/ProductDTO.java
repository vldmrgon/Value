package com.shared.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class ProductDTO {

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
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
    @AllArgsConstructor
    public static class Response implements Serializable {
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