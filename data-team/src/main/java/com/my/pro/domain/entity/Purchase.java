package com.my.pro.domain.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Document(collection = "purchases")
public class Purchase {
    @Id
    private String id;
    private Date purchaseDate;
    @DBRef
    private Shopper shopper;
    @DBRef
    private Product product;
}