package com.example.user.exception

import org.springframework.context.MessageSourceResolvable
import org.springframework.http.HttpStatus

interface ApiExceptionMessageSource : MessageSourceResolvable {
    val errorCode: String
    val httpStatus: HttpStatus
}