package com.my.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableAutoConfiguration(
        exclude = {
                MongoAutoConfiguration.class,
                MongoDataAutoConfiguration.class,
                RedisAutoConfiguration.class
        }
)
public class DataTeam {
    public static void main(String[] args) {
        SpringApplication.run(DataTeam.class, args);
    }
}

