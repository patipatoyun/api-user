package com.example.user.service

import com.example.user.client.RandomUserClient
import com.example.user.config.RandomUserConfig
import com.example.user.model.client.RandomUserResponse
import com.example.user.model.client.exception.RandomUserClientException
import com.example.user.model.external.entity.AddressInfo
import com.example.user.model.external.entity.ExtUserProfileEntity
import com.example.user.model.profile.Gender
import com.example.user.repository.ExtUserProfileRepository
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertThrows
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
import org.springframework.http.HttpStatus
import java.util.Optional
import kotlin.test.assertNotNull

@Tag("fast")
@ExtendWith(MockitoExtension::class)
internal class ExtUserProfileServiceImplTest {

    @Mock
    lateinit var randomUserClient: RandomUserClient

    @Mock
    lateinit var extUserProfileRepository: ExtUserProfileRepository

    @Mock
    lateinit var randomUserConfig: RandomUserConfig

    @InjectMocks
    lateinit var subject: ExternalProfileServiceImpl

    private val mapper = jacksonObjectMapper()
    private val keyword = "keyword"
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

    private val randomUserResponseSuccessText =
        this::class.java.classLoader.getResource("random_use_response_success.json").readText(Charsets.UTF_8)
    private val randomUserResponseSuccess = mapper.readValue<RandomUserResponse>(randomUserResponseSuccessText)

    @Nested
    inner class ExtInquiryProfileResponseTest {

        @Test
        @Throws(Exception::class)
        fun shouldReturnExtInquiryProfileResponse() {

            whenever(extUserProfileRepository.findById(any())).thenReturn(Optional.empty())
            whenever(randomUserClient.inquiry(any())).thenReturn(randomUserResponseSuccess)
            whenever(randomUserConfig.expiredTime).thenReturn(expireTime)
            whenever(extUserProfileRepository.save(any<ExtUserProfileEntity>())).thenReturn(extUserProfileEntity)

            val actual = subject.inquiry(keyword)
            assertNotNull(actual)

            argumentCaptor<ExtUserProfileEntity>().apply {
                verify(extUserProfileRepository).save(capture())
                assertThat(firstValue.title, `is`("Mrs"))
                assertThat(firstValue.name, `is`("Alice"))
                assertThat(firstValue.lastName, `is`("Tucker"))
                assertThat(firstValue.gender, `is`(Gender.FEMALE))
                assertThat(firstValue.phoneNumber, `is`("031-340-0525"))
                assertThat(firstValue.email, `is`("alice.tucker@example.com"))
                assertThat(firstValue.expiryTime, `is`(10))
                assertThat(firstValue.address.city, `is`("Limerick"))
                assertThat(firstValue.address.country, `is`("Ireland"))
                assertThat(firstValue.address.postcode, `is`("19160"))
            }

            verify(extUserProfileRepository, times(1)).findById(any())
            verify(randomUserClient, times(1)).inquiry(any())
            verify(extUserProfileRepository, times(1)).save(any())
        }

        @Test
        @Throws(Exception::class)
        fun shouldReturnExtInquiryProfileResponse_FromCacheable() {

            whenever(extUserProfileRepository.findById(any())).thenReturn(Optional.of(extUserProfileEntity))

            val actual = subject.inquiry(keyword)

            assertThat(actual.userInfo.name, `is`("firstName"))
            assertThat(actual.userInfo.lastname, `is`("lastName"))
            assertThat(actual.userInfo.gender, `is`(Gender.MALE))
            assertThat(actual.userInfo.email, `is`("email"))
            assertThat(actual.userInfo.phoneNumber, `is`("phoneNumber"))

            assertNotNull(actual.userInfo.address)

            verify(extUserProfileRepository, times(1)).findById(any())
            verify(randomUserClient, never()).inquiry(any())
            verify(extUserProfileRepository, never()).save(any())

        }

        @Test
        fun shouldThrowRandomUserClientException() {

            whenever(extUserProfileRepository.findById(any())).thenReturn(Optional.empty())
            whenever(randomUserClient.inquiry(any())).thenThrow(RandomUserClientException(message = "exception"))

            val actual = assertThrows(RandomUserClientException::class.java) {
                subject.inquiry(keyword)
            }

            assertThat(actual.message, `is`("exception"))
            assertThat(actual.httpStatus, `is`(HttpStatus.PRECONDITION_FAILED))
        }

        @Test
        fun shouldThrowRandomUserClientException_WhenRandomUserResultListIsEmpty() {

            whenever(extUserProfileRepository.findById(any())).thenReturn(Optional.empty())
            whenever(randomUserClient.inquiry(any())).thenReturn(RandomUserResponse(emptyList()))

            val actual = assertThrows(RandomUserClientException::class.java) {
                subject.inquiry(keyword)
            }

            assertThat(actual.message, `is`("RandomUser Not Found"))
            assertThat(actual.httpStatus, `is`(HttpStatus.PRECONDITION_FAILED))
        }
    }
}