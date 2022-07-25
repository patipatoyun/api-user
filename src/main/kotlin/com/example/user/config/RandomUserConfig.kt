package com.example.user.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "random-user")
class RandomUserConfig {
    var expiredTime: Long = 10L
}