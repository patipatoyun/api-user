package com.example.user.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.convert.DurationUnit
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import java.time.Duration
import java.time.temporal.ChronoUnit

@Configuration
@EnableCaching
class CacheConfig {

    @DurationUnit(ChronoUnit.HOURS)
    var userProfileExpTime = Duration.ofMinutes(10)

    @Autowired
    lateinit var cacheRedisConnectionFactory: RedisConnectionFactory

    @Bean
    fun defaultRedisCacheConfiguration(): RedisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
        .serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer<Any>(
                GenericJackson2JsonRedisSerializer()
            )
        )

    @Bean
    fun defaultRedisCacheManager(cacheConfiguration: RedisCacheConfiguration): CacheManager = RedisCacheManager
        .builder(cacheRedisConnectionFactory)
        .initialCacheNames(setOf(userProfile))
        .withInitialCacheConfigurations(
            mapOf(
                userProfile to RedisCacheConfiguration.defaultCacheConfig().entryTtl(userProfileExpTime)
            )
        ).build()

    companion object {
        const val userProfile = "userProfile"
    }
}