package com.example.user.model.external

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class ExtUserAddressDto(
    val city: String,
    val state: String,
    val country: String,
    val postcode: String
)