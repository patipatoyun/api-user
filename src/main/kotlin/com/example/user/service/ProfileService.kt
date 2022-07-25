package com.example.user.service

import com.example.user.model.profile.request.CreateProfileRequest
import com.example.user.model.profile.request.UpdateProfileRequest
import com.example.user.model.profile.response.CreateProfileResponse
import com.example.user.model.profile.response.InquiryProfileResponse
import com.example.user.model.profile.response.UpdateProfileResponse

interface ProfileService {

    fun inquiry(username: String, cid: String): InquiryProfileResponse
    fun create(request: CreateProfileRequest): CreateProfileResponse
    fun update(request: UpdateProfileRequest): UpdateProfileResponse
}
