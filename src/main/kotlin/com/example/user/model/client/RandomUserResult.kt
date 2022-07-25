package com.example.user.model.client

import com.example.user.deserializer.GenderDeserializer
import com.example.user.model.profile.Gender
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonIgnoreProperties(ignoreUnknown = true)
data class RandomUserResult(

    @JsonProperty("gender")
    @JsonDeserialize(using = GenderDeserializer::class)
    val gender: Gender,

    @JsonProperty("name")
    val randomUser: RandomUserName,

    @JsonProperty("location")
    val randomUserLocation: RandomUserLocation,

    @JsonProperty("phone")
    val phoneNumber: String,

    @JsonProperty("cell")
    val cellPhoneNumber: String,

    @JsonProperty("email")
    val email: String
)
