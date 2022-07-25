package com.example.user.deserializer

import com.example.user.model.profile.Gender
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import org.mockito.kotlin.whenever
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class GenderDeserializerTest {

    @InjectMocks
    private lateinit var deserializer: GenderDeserializer

    @Mock
    private lateinit var parser: JsonParser

    @Mock
    private lateinit var ctxt: DeserializationContext

    @Test
    @Throws(Exception::class)
    fun shouldReturnMale() {
        whenever(parser.text).thenReturn("male")
        val actual = deserializer.deserialize(parser, ctxt)
        assertThat(actual, `is`(Gender.MALE))
    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnFemale() {
        whenever(parser.text).thenReturn("female")
        val actual = deserializer.deserialize(parser, ctxt)
        assertThat(actual, `is`(Gender.FEMALE))

    }

    @Test
    @Throws(Exception::class)
    fun shouldReturnUndefined() {
        whenever(parser.text).thenReturn("99999")
        val actual = deserializer.deserialize(parser, ctxt)
        assertThat(actual, `is`(Gender.UNDEFINED))
    }
}