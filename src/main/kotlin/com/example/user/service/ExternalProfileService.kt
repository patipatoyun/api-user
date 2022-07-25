package com.example.user.service

import com.example.user.model.external.ExtInquiryProfileResponse

interface ExternalProfileService {
    fun inquiry(keyword: String): ExtInquiryProfileResponse
}
