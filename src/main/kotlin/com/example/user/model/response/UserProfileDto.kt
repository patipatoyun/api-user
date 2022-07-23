package com.example.user.model.response

import com.example.user.model.GENDER
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class UserProfileDto(
    val id: Int,
    val name: String,
    val surname: String,
    val cid: String? = null,
    val address: String? = null,
    val gender: GENDER,
    val phoneNumber: String? = null,
    val email: String? = null
)