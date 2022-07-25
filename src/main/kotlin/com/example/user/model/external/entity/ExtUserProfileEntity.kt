package com.example.user.model.external.entity

import com.example.user.model.profile.Gender
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import java.io.Serializable
import java.util.concurrent.TimeUnit

@RedisHash("extUserProfile")
class ExtUserProfileEntity(
    @Id
    val id: String,
    val title: String,
    val name: String,
    val lastName: String,
    val gender: Gender,
    val phoneNumber: String,
    val email: String,
    val address: AddressInfo,
    @TimeToLive(unit = TimeUnit.MINUTES)
    var expiryTime: Long
) : Serializable