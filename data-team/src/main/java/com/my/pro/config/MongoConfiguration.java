package com.my.pro.config;

import com.mongodb.client.MongoClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.beans.factory.annotation.Value;

import com.mongodb.client.MongoClients;
import com.mongodb.MongoClientSettings;
import com.mongodb.ConnectionString;
import com.mongodb.MongoCredential;

@Configuration
@EnableMongoRepositories(basePackages = "com.my.pro.repository")
public class MongoConfiguration {

    @Value("${spring.data.mongodb.authentication-database}")
    private String database;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private char[] password;

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoCredential credential = MongoCredential.createCredential(username, database, password);
        ConnectionString connectionString = new ConnectionString(mongoUri);

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .credential(credential)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        return new MongoTemplate(mongoClient, database);
    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
//        return new MongoTransactionManager(dbFactory);
//    }
}
