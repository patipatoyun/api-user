package com.example.user.extension

import com.example.user.model.entity.UserProfile
import com.example.user.model.profile.response.UserProfileDto

fun UserProfile.toUserProfileDto(): UserProfileDto {
    return UserProfileDto(
        id = id,
        name = name,
        surname = surname,
        cid = cid,
        address = address,
        gender = gender,
        phoneNumber = phoneNumber,
        email = email
    )
}