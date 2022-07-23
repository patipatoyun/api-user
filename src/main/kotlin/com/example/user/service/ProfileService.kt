package com.example.user.service

import com.example.user.model.request.CreateProfileRequest
import com.example.user.model.request.UpdateProfileRequest
import com.example.user.model.response.CreateProfileResponse
import com.example.user.model.response.InquiryProfileResponse
import com.example.user.model.response.UpdateProfileResponse

interface ProfileService {

    fun inquiry(username: String, cid: String): InquiryProfileResponse
    fun create(request: CreateProfileRequest): CreateProfileResponse
    fun update(request: UpdateProfileRequest): UpdateProfileResponse
}
