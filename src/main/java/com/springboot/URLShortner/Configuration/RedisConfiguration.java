package com.springboot.URLShortner.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.URLShortner.Model.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfiguration {

    @Autowired
    RedisConnectionFactory connectionFactory;

    @Bean
    RedisTemplate<String, Url> redisTemplate()
    {
        final RedisTemplate<String, Url> redisTemplate = new RedisTemplate<>();
        Jackson2JsonRedisSerializer<Url> valueSerializer = new Jackson2JsonRedisSerializer<>(Url.class);
        valueSerializer.serialize(Url.builder().build());
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(valueSerializer);
        return redisTemplate;
    }
}
