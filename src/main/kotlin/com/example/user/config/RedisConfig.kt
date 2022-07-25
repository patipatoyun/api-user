package com.example.user.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {

    @Autowired
    lateinit var redisProperties: RedisProperties

    @Primary
    @Bean(name = ["cacheRedisConnectionFactory"])
    fun redisConnectionFactory(): RedisConnectionFactory {
        val standaloneConfiguration = RedisStandaloneConfiguration(redisProperties.host, redisProperties.port)
        standaloneConfiguration.database = redisProperties.database
        standaloneConfiguration.password = RedisPassword.of(redisProperties.password)
        val clientConfig = JedisClientConfiguration.builder().useSsl().build()
        return JedisConnectionFactory(standaloneConfiguration, clientConfig)
    }

    @Primary
    @Bean(name = ["cacheRedisTemplate"])
    fun redisTemplate(): RedisTemplate<Any, Any> {
        val redisTemplate = RedisTemplate<Any, Any>()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.setDefaultSerializer(GenericJackson2JsonRedisSerializer())
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.hashKeySerializer = GenericJackson2JsonRedisSerializer()
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer()
        return redisTemplate
    }
}