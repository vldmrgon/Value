package com.my.pro.config;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@Configuration
@EnableMongoRepositories(basePackages = "com.my.pro.repository")
public class MongoConfiguration {

    @Value("${spring.data.mongodb.authentication-database}")
    private String mongoDatabase;

    @Value("${spring.data.mongodb.username}")
    private String mongoUsername;

    @Value("${spring.data.mongodb.password}")
    private char[] mongoPassword;

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.mongo.port}")
    private int mongoPort;

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(
                createMongoUri(mongoUsername, mongoPassword, mongoHost, mongoPort, mongoDatabase)
        );
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory());
    }

    @Bean
    public MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTransactionManager(mongoDatabaseFactory);
    }

    public String createMongoUri(String username, char[] password, String host, int port, String databaseName) {
        return "mongodb://" + username + ":" + Arrays.toString(password) + "@" + host + ":" + port + "/" + databaseName;
    }
}