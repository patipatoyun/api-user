package com.example.user.extension

import com.example.user.model.external.entity.AddressInfo
import com.example.user.model.external.entity.ExtUserProfileEntity
import com.example.user.model.profile.Gender
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ExtUserProfileExtensionKtTest {

    private val postcode = "postcode"
    private val country = "country"
    private val state = "state"
    private val city = "city"
    private val title = "title"
    private val firstName = "firstName"
    private val lastName = "lastName"
    private val phoneNumber = "phoneNumber"
    private val email = "email"
    private val gender = Gender.MALE
    private val id = "id"
    private val expireTime: Long = 10

    private val extUserProfileEntity = ExtUserProfileEntity(
        id = id,
        title = title,
        name = firstName,
        gender = gender,
        lastName = lastName,
        phoneNumber = phoneNumber,
        email = email,
        address = AddressInfo(
            city = city,
            state = state,
            country = country,
            postcode = postcode
        ),
        expiryTime = expireTime
    )

    @Nested
    inner class ToUserProfileDtoTest {

        @Test
        @Throws(Exception::class)
        fun shouldReturnToUserProfileDto() {
            val actual = extUserProfileEntity.toExtUserProfileDto()

            assertThat(actual.title, `is`("title"))
            assertThat(actual.name, `is`("firstName"))
            assertThat(actual.lastname, `is`("lastName"))
            assertThat(actual.gender, `is`(Gender.MALE))
            assertThat(actual.phoneNumber, `is`("phoneNumber"))
            assertThat(actual.email, `is`("email"))

            assertThat(actual.address.city, `is`("city"))
            assertThat(actual.address.country, `is`("country"))
            assertThat(actual.address.postcode, `is`("postcode"))
            assertThat(actual.address.state, `is`("state"))
        }
    }
}

