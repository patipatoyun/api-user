package com.example.user.model.client

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class RandomUserLocation(

    @JsonProperty("city")
    val city: String,

    @JsonProperty("state")
    val state: String,

    @JsonProperty("country")
    val country: String,

    @JsonProperty("postcode")
    val postcode: String,
)
