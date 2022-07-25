package com.example.user.model.client.exception

import com.example.user.exception.ApiExceptionMessageSource
import org.springframework.http.HttpStatus

class RandomUserClientException(
    override val message: String
) : RuntimeException(), ApiExceptionMessageSource {
    override val errorCode = "RUC01"
    override val httpStatus = HttpStatus.PRECONDITION_FAILED
    override fun getCodes(): Array<String>? = arrayOf("default_error")
}
