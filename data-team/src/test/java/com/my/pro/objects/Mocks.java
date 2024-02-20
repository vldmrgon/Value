package com.my.pro.objects;

import com.my.pro.domain.entity.Purchase;
import com.my.pro.domain.entity.Product;
import com.my.pro.domain.entity.Shopper;

import lombok.experimental.UtilityClass;

import com.shared.dto.PurchaseDTO;
import com.shared.dto.ShopperDTO;
import com.shared.dto.ProductDTO;

import java.util.Date;

@UtilityClass
public class Mocks {

    public String SHOPPER_EMAIL = "test@example.com";
    public String SHOPPER_FIRST_NAME = "first-name";
    public String SHOPPER_LAST_NAME = "last-name";

    public String PRODUCT_CATEGORY = "test-category";
    public String PRODUCT_MODEL = "test-model";
    public String PRODUCT_BRAND = "test-brand";

    public Date DATE = new Date();
    public String ID = "id";

    public ProductDTO.Request REQUEST_PRODUCT_DTO;
    public ProductDTO.Response RESPONSE_PRODUCT_DTO;

    public ShopperDTO.Request REQUEST_SHOPPER_DTO;
    public ShopperDTO.Response RESPONSE_SHOPPER_DTO;

    public PurchaseDTO.Request REQUEST_PURCHASE_DTO;
    public PurchaseDTO.Response RESPONSE_PURCHASE_DTO;

    public Purchase PURCHASE;
    public Product PRODUCT;
    public Shopper SHOPPER;

    static {
        REQUEST_PRODUCT_DTO = new ProductDTO.Request(PRODUCT_MODEL, PRODUCT_CATEGORY, PRODUCT_BRAND);
        RESPONSE_PRODUCT_DTO = new ProductDTO.Response(ID, PRODUCT_MODEL, PRODUCT_CATEGORY, PRODUCT_BRAND);

        REQUEST_SHOPPER_DTO = new ShopperDTO.Request(SHOPPER_EMAIL, SHOPPER_FIRST_NAME, SHOPPER_LAST_NAME);
        RESPONSE_SHOPPER_DTO = new ShopperDTO.Response(ID, SHOPPER_EMAIL, SHOPPER_FIRST_NAME, SHOPPER_LAST_NAME);

        REQUEST_PURCHASE_DTO = new PurchaseDTO.Request(REQUEST_SHOPPER_DTO, REQUEST_PRODUCT_DTO);
        RESPONSE_PURCHASE_DTO = new PurchaseDTO.Response(ID, DATE, RESPONSE_SHOPPER_DTO, RESPONSE_PRODUCT_DTO);

        PRODUCT = new Product(ID, PRODUCT_MODEL, PRODUCT_CATEGORY, PRODUCT_BRAND);
        SHOPPER = new Shopper(ID, SHOPPER_EMAIL, SHOPPER_FIRST_NAME, SHOPPER_LAST_NAME);
        PURCHASE = new Purchase(ID, DATE, SHOPPER, PRODUCT);
    }
}