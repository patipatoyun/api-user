package com.example.user.repository

import com.example.user.config.CacheConfig
import com.example.user.model.entity.UserProfile
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.CrudRepository

interface UserProfileRepository : CrudRepository<UserProfile, Int> {
    @Cacheable(CacheConfig.userProfile, key = "{#username,#cid}", unless = "#result == null")
    fun findByNameAndCid(username: String, cid: String): UserProfile?
}