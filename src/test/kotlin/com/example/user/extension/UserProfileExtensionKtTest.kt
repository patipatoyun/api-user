package com.example.user.extension

import com.example.user.model.profile.Gender
import com.example.user.model.entity.UserProfile
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class UserProfileExtensionKtTest {

    private val userProfile = UserProfile(
        id = 1,
        name = "name-01",
        surname = "surname-01",
        cid = "cid-01",
        address = "address",
        gender = Gender.MALE,
        phoneNumber = "phoneNumber",
        email = "email"
    )

    @Nested
    inner class ToUserProfileDtoTest {

        @Test
        @Throws(Exception::class)
        fun shouldReturnToUserProfileDto() {
            val actual = userProfile.toUserProfileDto()
            assertThat(actual.id, `is`(1))
            assertThat(actual.cid, `is`("cid-01"))
            assertThat(actual.name, `is`("name-01"))
            assertThat(actual.surname, `is`("surname-01"))
        }
    }
}
