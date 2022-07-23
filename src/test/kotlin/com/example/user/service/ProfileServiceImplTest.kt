package com.example.user.service

import com.example.user.model.GENDER
import com.example.user.model.entity.UserProfile
import com.example.user.model.request.CreateProfileRequest
import com.example.user.model.request.UpdateProfileRequest
import com.example.user.repository.UserProfileRepository
import com.example.user.exception.UserProfileNotFoundException
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@Tag("fast")
@ExtendWith(MockitoExtension::class)
internal class ProfileServiceImplTest {

    @Mock
    lateinit var userProfileRepository: UserProfileRepository

    @InjectMocks
    lateinit var subject: ProfileServiceImpl

    private val userName = "userName"
    private val cid = "cid"

    private val userProfile = UserProfile(
        id = 1,
        name = "name-01",
        surname = "surname-01",
        cid = "cid-01",
        address = "address",
        gender = GENDER.MALE,
        phoneNumber = "phoneNumber",
        email = "email"
    )

    @Nested
    inner class InquiryUserProfileTest {

        @Test
        @Throws(Exception::class)
        fun shouldThrowUserEntityNotFoundException() {
            whenever(userProfileRepository.findByNameAndCid(any(), any())).thenReturn(null)
            Assertions.assertThrows(UserProfileNotFoundException::class.java) {
                subject.inquiry(userName, cid)
            }
        }

        @Test
        @Throws(Exception::class)
        fun shouldReturnInquiryUserResponse() {
            whenever(userProfileRepository.findByNameAndCid(any(), any())).thenReturn(userProfile)

            val actual = subject.inquiry(userName, cid)

            assertThat(actual.userInfo.id, `is`(1))
            assertThat(actual.userInfo.cid, `is`("cid-01"))
            assertThat(actual.userInfo.name, `is`("name-01"))
            assertThat(actual.userInfo.surname, `is`("surname-01"))

        }
    }

    @Nested
    inner class CreateUserProfileTest {

        val request = CreateProfileRequest(
            name = "name-01",
            surname = "surname-01",
            cid = "cid-01",
            address = "address",
            gender = GENDER.MALE,
            phoneNumber = "phoneNumber",
            email = "email"
        )

        @Test
        @Throws(Exception::class)
        fun shouldReturnCreateUserResponse() {
            whenever(userProfileRepository.save(any<UserProfile>())).thenReturn(userProfile)

            val actual = subject.create(request)

            assertThat(actual.userInfo.id, `is`(1))
            assertThat(actual.userInfo.name, `is`("name-01"))
            assertThat(actual.userInfo.surname, `is`("surname-01"))
            assertThat(actual.userInfo.cid, `is`("cid-01"))

        }
    }

    @Nested
    inner class UpdateUserProfileTest {

        val request = UpdateProfileRequest(
            id = 1,
            name = "name-99",
            surname = "surname-99",
            address = "address",
            gender = GENDER.MALE,
            phoneNumber = "phoneNumber",
            email = "email"
        )

        @Test
        @Throws(Exception::class)
        fun shouldReturnUpdateUserResponse() {
            whenever(userProfileRepository.findById(any())).thenReturn(Optional.of(userProfile))
            whenever(userProfileRepository.save(any<UserProfile>())).thenReturn(userProfile)

            val actual = subject.update(request)
            assertNotNull(actual)

            argumentCaptor<UserProfile>().apply {
                verify(userProfileRepository).save(capture())
                assertThat(firstValue.name, `is`("name-99"))
                assertThat(firstValue.surname, `is`("surname-99"))
            }

            verify(userProfileRepository, times(1)).save(any<UserProfile>())
        }

        @Test
        @Throws(Exception::class)
        fun shouldReturnUserEntityNotFoundException() {
            whenever(userProfileRepository.findById(any())).thenReturn(Optional.empty())
            Assertions.assertThrows(UserProfileNotFoundException::class.java) {
                subject.update(request)
            }
        }
    }
}