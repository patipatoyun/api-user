package com.example.user.config

import com.example.user.model.client.exception.RandomUserClientException
import com.fasterxml.jackson.databind.ObjectMapper
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.context.MessageSource
import java.lang.Exception
import java.util.Locale

class FeignRandomUserErrorDecoder(
    private val messageSource: MessageSource,
    private val objectMapper: ObjectMapper
) : ErrorDecoder {

    override fun decode(methodKey: String?, response: Response): Exception {
        if (methodKey != null) {
            when {
                methodKey.contains("RandomUserClient#inquiry", ignoreCase = true) -> handlerError(response)
            }
        }
        return FeignException.errorStatus(methodKey, response)
    }

    private fun handlerError(response: Response) {
        val message = errorMessage(response)
        if (response.status() != 200) throw RandomUserClientException(message = message)
    }

    private fun errorMessage(response: Response) = try {
        objectMapper.readTree(response.body().asInputStream()).get("error").asText()
    } catch (ex: Exception) {
        messageSource.getMessage("default_error", null, Locale.ENGLISH)
    }.orEmpty()
}
