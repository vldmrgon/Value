package com.shared.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ShopperDTO {

    @Setter
    @Getter
    @Builder
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
    public static class Response {
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