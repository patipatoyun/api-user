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

@Configuration
class RedisConfig {

    @Autowired
    lateinit var redisProperties: RedisProperties

    @Primary
    @Bean(name = ["userRedisConnectionFactory"])
    fun redisConnectionFactory(): RedisConnectionFactory {
        val standaloneConfiguration = RedisStandaloneConfiguration(redisProperties.host, redisProperties.port)
        standaloneConfiguration.database = redisProperties.database
        standaloneConfiguration.password = RedisPassword.of(redisProperties.password)
        val clientConfig = JedisClientConfiguration.builder().useSsl().build()
        return JedisConnectionFactory(standaloneConfiguration, clientConfig)
    }
}