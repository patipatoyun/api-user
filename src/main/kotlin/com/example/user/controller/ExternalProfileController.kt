package com.example.user.controller

import com.example.user.model.profile.response.BaseResponse
import com.example.user.model.external.ExtInquiryProfileResponse
import com.example.user.service.ExternalProfileService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/external/profile")
class ExternalProfileController(
    private val externalProfileService: ExternalProfileService
) {

    @GetMapping(
        value = ["/inquiry"]
    )
    @ApiOperation(value = "inquiry for external user profile")
    @ApiResponses(
        ApiResponse(code = 200, message = "Success.")
    )
    fun inquiry(
        @RequestParam(value = "keyword") keyword: String
    ): BaseResponse<ExtInquiryProfileResponse> {
        return BaseResponse.Builder<ExtInquiryProfileResponse>().success(
            externalProfileService.inquiry(keyword)
        )
    }
}

