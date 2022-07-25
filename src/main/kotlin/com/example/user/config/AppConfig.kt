package com.example.user.config

import com.example.user.client.RandomUserClient
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration


@EnableAutoConfiguration
@Configuration
@ConfigurationProperties
@EnableFeignClients(
    clients = [
        RandomUserClient::class
    ]
)
class AppConfig
