package com.example.user.repository

import com.example.user.model.external.entity.ExtUserProfileEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ExtUserProfileRepository : CrudRepository<ExtUserProfileEntity, String>
