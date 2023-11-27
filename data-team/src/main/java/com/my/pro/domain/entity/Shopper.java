package com.my.pro.domain.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Document(collection = "shoppers")
public class Shopper {
    @Id
    private String id;
    private String email;
    private String firstName;
    private String lastName;
}