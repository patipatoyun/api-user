package com.example.user.model.profile

enum class Gender(val value: String) {
    MALE("male"),
    FEMALE("female"),
    UNDEFINED("");

    companion object {
        fun valueFromReq(request: String): Gender? {
            return Gender.values().firstOrNull {
                it.value == request
            }
        }
    }
}