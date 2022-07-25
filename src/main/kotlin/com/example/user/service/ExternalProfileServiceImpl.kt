package com.example.user.service

import com.example.user.client.RandomUserClient
import com.example.user.config.LoggerDelegate
import com.example.user.config.RandomUserConfig
import com.example.user.extension.toExtUserProfileDto
import com.example.user.model.client.RandomUserResult
import com.example.user.model.client.exception.RandomUserClientException
import com.example.user.model.external.ExtInquiryProfileResponse
import com.example.user.model.external.entity.AddressInfo
import com.example.user.model.external.entity.ExtUserProfileEntity
import com.example.user.repository.ExtUserProfileRepository
import org.springframework.stereotype.Service

@Service
class ExternalProfileServiceImpl(
    private val randomUserClient: RandomUserClient,
    private val extUserProfileRepository: ExtUserProfileRepository,
    private val randomUserConfig: RandomUserConfig
) : ExternalProfileService {

    val log by LoggerDelegate()

    override fun inquiry(keyword: String): ExtInquiryProfileResponse {

        val entity = extUserProfileRepository.findById(keyword).orElse(null)
        if (entity != null) return ExtInquiryProfileResponse(userInfo = entity.toExtUserProfileDto())

        val randomUserResultList = callRandomUserClient(keyword)
        if (randomUserResultList.isNullOrEmpty()) throw RandomUserClientException("RandomUser Not Found")

        return randomUserResultList.first().let {
            val entity = saveCache(keyword, it)
            ExtInquiryProfileResponse(userInfo = entity.toExtUserProfileDto())
        }
    }

    internal fun saveCache(keyword: String, it: RandomUserResult): ExtUserProfileEntity {
        return ExtUserProfileEntity(
            id = keyword,
            title = it.randomUser.title,
            name = it.randomUser.firstname,
            lastName = it.randomUser.lastname,
            gender = it.gender,
            phoneNumber = it.phoneNumber,
            email = it.email,
            address = AddressInfo(
                city = it.randomUserLocation.city,
                state = it.randomUserLocation.state,
                country = it.randomUserLocation.country,
                postcode = it.randomUserLocation.postcode
            ),
            expiryTime = randomUserConfig.expiredTime
        ).run {
            extUserProfileRepository.save(this)
        }
    }

    internal fun callRandomUserClient(keyword: String) = try {
        randomUserClient.inquiry(keyword).results
    } catch (ex: RandomUserClientException) {
        log.error("[RandomUserClientException] cause: {}", ex.message)
        throw ex
    } catch (ex: Exception) {
        log.error("[Call RandomUser Fail] cause: {}", ex.message)
        throw ex
    }
}