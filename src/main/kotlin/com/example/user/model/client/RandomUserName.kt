package com.example.user.model.client

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class RandomUserName(

    @JsonProperty("title")
    val title: String,

    @JsonProperty("first")
    val firstname: String,

    @JsonProperty("last")
    val lastname: String
)
