package com.example.user.model.profile.request

import com.example.user.model.profile.Gender
import javax.validation.constraints.Size

class CreateProfileRequest(
    val name: String,
    val surname: String,
    @field:Size(min = 13, max = 13)
    val cid: String,
    val address: String?,
    val gender: Gender,
    val phoneNumber: String?,
    val email: String?
)
