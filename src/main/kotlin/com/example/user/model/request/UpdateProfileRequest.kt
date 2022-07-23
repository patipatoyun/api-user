package com.example.user.model.request

import com.example.user.model.GENDER

class UpdateProfileRequest(
    val id: Int,
    val name: String,
    val surname: String,
    val address: String?,
    val gender: GENDER,
    val phoneNumber: String?,
    val email: String?
)
