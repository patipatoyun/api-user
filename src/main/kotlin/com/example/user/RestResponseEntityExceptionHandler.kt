package com.example.user

import com.example.user.config.LoggerDelegate
import com.example.user.model.response.BaseResponse
import com.example.user.exception.UserProfileNotFoundException
import org.springframework.context.MessageSource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.Locale

@ControllerAdvice
class RestResponseEntityExceptionHandler(
    val messageSource: MessageSource
) : ResponseEntityExceptionHandler() {

    val log by LoggerDelegate()

    @ExceptionHandler(UserProfileNotFoundException::class)
    fun userEntityNotFoundExceptionHandler(
        ex: UserProfileNotFoundException,
        locale: Locale
    ): ResponseEntity<BaseResponse<String>> {
        log.error("[UserProfileNotFoundException]")
        val response = BaseResponse.Builder<String>().error(ex.errorCode, messageSource.getMessage(ex, locale))
        return ResponseEntity(response, ex.httpStatus)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors = mutableListOf<String>()
        ex.bindingResult.fieldErrors.forEach { errors.add("${it.field}: ${it.defaultMessage}") }
        ex.bindingResult.globalErrors.forEach { errors.add("${it.objectName}: ${it.defaultMessage}") }
        log.error("[MethodArgumentNotValidException] errors: {}", errors)
        val response = BaseResponse.Builder<List<String>>()
            .error(
                "VAL01",
                "Validation failed", null
            )
        return ResponseEntity(response, status)
    }


    @ExceptionHandler(Exception::class)
    fun defaultException(ex: Exception, req: WebRequest, locale: Locale): ResponseEntity<BaseResponse<String>> {
        log.error("defaultException: ", ex)
        val response =
            BaseResponse.Builder<String>().error(
                "ERR01", "defaultException", null, ex.message
            )
        return ResponseEntity(response, HttpStatus.BAD_REQUEST)
    }

}
