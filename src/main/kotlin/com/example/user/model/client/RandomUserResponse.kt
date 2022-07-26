package com.example.user.model.client

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class RandomUserResponse(
    val results: List<RandomUserResult> = emptyList()
)
