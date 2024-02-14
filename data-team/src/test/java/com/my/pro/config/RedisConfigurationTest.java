package com.my.pro.config;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.core.RedisTemplate;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import org.assertj.core.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class RedisConfigurationTest {

    @Mock
    private RedisConnectionFactory connectionFactory;
    @InjectMocks
    private RedisConfiguration redisConfiguration;

    @Test
    public void redisTemplateIsCorrectlyConfigured() {

        RedisTemplate<String, Object> redisTemplate = redisConfiguration.redisTemplate(connectionFactory);

        Assertions.assertThat(redisTemplate.getConnectionFactory()).isEqualTo(connectionFactory);
        Assertions.assertThat(redisTemplate).isNotNull();

        Assertions.assertThat(redisTemplate.getKeySerializer()).isInstanceOf(StringRedisSerializer.class);
        Assertions.assertThat(redisTemplate.getHashKeySerializer()).isInstanceOf(StringRedisSerializer.class);

        Assertions.assertThat(redisTemplate.getValueSerializer()).isInstanceOf(JdkSerializationRedisSerializer.class);
        Assertions.assertThat(redisTemplate.getHashValueSerializer()).isInstanceOf(JdkSerializationRedisSerializer.class);
    }
}