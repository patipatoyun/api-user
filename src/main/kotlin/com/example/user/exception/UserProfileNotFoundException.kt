package com.example.user.exception

import org.springframework.http.HttpStatus

class UserProfileNotFoundException : RuntimeException(), ApiExceptionMessageSource {
    override val errorCode = "USER01"
    override val httpStatus = HttpStatus.NO_CONTENT
    override fun getCodes(): Array<String>? = arrayOf("default_error")
}
