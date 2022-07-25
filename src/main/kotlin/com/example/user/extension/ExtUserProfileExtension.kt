package com.example.user.extension

import com.example.user.model.external.ExtUserAddressDto
import com.example.user.model.external.ExtUserProfileDto
import com.example.user.model.external.entity.ExtUserProfileEntity

fun ExtUserProfileEntity.toExtUserProfileDto(): ExtUserProfileDto {
    return ExtUserProfileDto(
        title = this.title,
        name = this.name,
        lastname = this.lastName,
        gender = this.gender,
        phoneNumber = this.phoneNumber,
        email = this.email,
        address = ExtUserAddressDto(
            city = this.address.city,
            state = this.address.state,
            country = this.address.country,
            postcode = this.address.postcode
        )
    )
}