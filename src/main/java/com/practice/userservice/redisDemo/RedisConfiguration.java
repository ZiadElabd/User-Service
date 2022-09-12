package com.practice.userservice.redisDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
@Slf4j
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String REDIS_HOSTNAME;

    @Value("${spring.redis.port}")
    private int REDIS_PORT;

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.setHashKeySerializer(new GenericToStringSerializer<>(String.class));

        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());

        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);

        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().build();

        JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);

        factory.afterPropertiesSet();

        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setEnableTransactionSupport(true);

        log.error("REDI");

        return redisTemplate;
    }
}
