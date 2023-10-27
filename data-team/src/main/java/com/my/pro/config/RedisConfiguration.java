package com.my.pro.config;

import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.connection.*;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.master.port}")
    private int redisMasterPort;

    @Value("${spring.redis.slave1.port}")
    private int redisSlave1Port;

    @Value("${spring.redis.slave1.port}")
    private int redisSlave2Port;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.host}")
    private String redisHost;

    @Bean
    public RedisConnectionFactory lettuceConnectionFactory() {

        RedisNode master = RedisNode.newRedisNode()
                .withName("master")
                .listeningAt(redisHost, redisMasterPort)
                .promotedAs(RedisNode.NodeType.MASTER)
                .build();

        RedisNode slaveNode1 = RedisNode.newRedisNode()
                .withName("slave-1")
                .listeningAt(redisHost, redisSlave1Port)
                .replicaOf("master")
                .build();

        RedisNode slaveNode2 = RedisNode.newRedisNode()
                .withName("slave-2")
                .listeningAt(redisHost, redisSlave2Port)
                .replicaOf("master")
                .build();

        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.addClusterNode(master);
//        redisClusterConfiguration.addClusterNode(slaveNode1);
//        redisClusterConfiguration.addClusterNode(slaveNode2);
        redisClusterConfiguration.setPassword(redisPassword);

        return new LettuceConnectionFactory(redisClusterConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}