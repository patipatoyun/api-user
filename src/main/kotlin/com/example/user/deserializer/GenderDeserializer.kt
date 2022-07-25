package com.example.user.deserializer

import com.example.user.config.LoggerDelegate
import com.example.user.model.profile.Gender
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

class GenderDeserializer : JsonDeserializer<Gender>() {

    private val log by LoggerDelegate()

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Gender {
        return try {
            p?.text?.let { Gender.valueFromReq(it) } ?: Gender.UNDEFINED
        } catch (ex: Exception) {
            log.error("[JsonDeserializer Exception] message: {}", ex.message)
            throw ex
        }
    }
}