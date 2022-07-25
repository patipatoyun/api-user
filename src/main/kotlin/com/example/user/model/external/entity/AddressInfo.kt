package com.example.user.model.external.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
class AddressInfo(
    val city: String,
    val state: String,
    val country: String,
    val postcode: String
) : Serializable
