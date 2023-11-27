package com.shared.dto;

import javax.validation.constraints.*;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ShopperDTO {

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Request {
        @Email
        @NotNull
        private String email;
        @NotNull
        @NotBlank
        private String firstName;
        @NotNull
        @NotBlank
        private String lastName;
    }

    @Setter
    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response implements Serializable{
        @NotNull
        private String id;
        @Email
        @NotNull
        private String email;
        @NotNull
        @NotBlank
        private String firstName;
        @NotNull
        @NotBlank
        private String lastName;
    }
}