package com.example.user.service

import com.example.user.config.LoggerDelegate
import com.example.user.extension.toUserProfileDto
import com.example.user.model.entity.UserProfile
import com.example.user.model.profile.request.CreateProfileRequest
import com.example.user.model.profile.request.UpdateProfileRequest
import com.example.user.model.profile.response.CreateProfileResponse
import com.example.user.model.profile.response.InquiryProfileResponse
import com.example.user.model.profile.response.UpdateProfileResponse
import com.example.user.repository.UserProfileRepository
import com.example.user.exception.UserProfileNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfileServiceImpl(
    private val userProfileRepository: UserProfileRepository
) : ProfileService {

    val log by LoggerDelegate()

    override fun inquiry(username: String, cid: String): InquiryProfileResponse {
        val userEntity = userProfileRepository.findByNameAndCid(username, cid)
            ?: throw UserProfileNotFoundException()

        return InquiryProfileResponse(
            userInfo = userEntity.toUserProfileDto()
        )
    }

    @Transactional
    override fun create(request: CreateProfileRequest): CreateProfileResponse {
        val entity = UserProfile().apply {
            name = request.name
            surname = request.surname
            cid = request.cid
            address = request.address
            gender = request.gender
            phoneNumber = request.phoneNumber
            email = request.email
        }

        userProfileRepository.save(entity).run {
            return CreateProfileResponse(
                userInfo = this.toUserProfileDto()
            )
        }
    }

    @Transactional
    override fun update(request: UpdateProfileRequest): UpdateProfileResponse {

        val entity = userProfileRepository.findById(request.id).orElse(null)
            ?: throw UserProfileNotFoundException()

        entity.name = request.name
        entity.surname = request.surname
        entity.address = request.address
        entity.gender = request.gender
        entity.phoneNumber = request.phoneNumber
        entity.email = request.email

        userProfileRepository.save(entity).run {
            return UpdateProfileResponse(
                userInfo = this.toUserProfileDto()
            )
        }
    }
}