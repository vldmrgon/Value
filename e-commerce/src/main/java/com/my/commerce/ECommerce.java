package com.my.commerce;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
public class ECommerce {
    public static void main(String[] args) {
        SpringApplication.run(ECommerce.class, args);
    }
}
