package com.my.pro.config;

import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SpringBootTest(classes = MongoConfiguration.class)
public class MongoConfigurationTest {

    @Autowired
    private ApplicationContext context;

    @Test
    public void mongoDatabaseFactoryBeanIsCorrectlyConfigured() {
        MongoDatabaseFactory mongoDatabaseFactory = context.getBean(MongoDatabaseFactory.class);
        Assertions.assertNotNull(mongoDatabaseFactory);
    }

    @Test
    public void mongoTemplateBeanIsCorrectlyConfigured() {
        MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);
        Assertions.assertNotNull((mongoTemplate));
    }

    @Test
    public void mongoTransactionManagerBeanIsCorrectlyConfigured() {
        MongoTransactionManager mongoTransactionManager = context.getBean(MongoTransactionManager.class);
        Assertions.assertNotNull(mongoTransactionManager);
    }

    @Test
    public void mongoUriIsCorrectlyFormed() {
        MongoConfiguration configuration = context.getBean(MongoConfiguration.class);
        String expectedUri = "mongodb://UnitTest:[U, n, i, t, T, e, s, t]@localhost:27017/db";
        String actualUri = configuration.createMongoUri(
                "UnitTest",
                "UnitTest".toCharArray(),
                "localhost",
                27017,
                "db"
        );
        Assertions.assertEquals(expectedUri, actualUri);
    }
}