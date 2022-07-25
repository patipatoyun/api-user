package com.example.user.model.profile.request

import com.example.user.model.profile.Gender

class UpdateProfileRequest(
    val id: Int,
    val name: String,
    val surname: String,
    val address: String?,
    val gender: Gender,
    val phoneNumber: String?,
    val email: String?
)
