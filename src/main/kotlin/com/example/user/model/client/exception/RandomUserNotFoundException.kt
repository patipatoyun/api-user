package com.example.user.model.client.exception

import com.example.user.exception.ApiExceptionMessageSource
import org.springframework.http.HttpStatus

class RandomUserNotFoundException : RuntimeException(), ApiExceptionMessageSource {
    override val errorCode = "RUC02"
    override val httpStatus = HttpStatus.NO_CONTENT
    override fun getCodes(): Array<String>? = arrayOf("default_error")
}
