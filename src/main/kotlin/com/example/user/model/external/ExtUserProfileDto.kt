package com.example.user.model.external

import com.example.user.model.profile.Gender
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ExtUserProfileDto(
    val title: String,
    val name: String,
    val lastname: String,
    val gender: Gender,
    val phoneNumber: String,
    val email: String,
    val address: ExtUserAddressDto
)