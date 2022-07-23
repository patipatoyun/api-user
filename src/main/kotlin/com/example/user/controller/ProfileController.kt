package com.example.user.controller

import com.example.user.model.request.CreateProfileRequest
import com.example.user.model.request.UpdateProfileRequest
import com.example.user.model.response.BaseResponse
import com.example.user.model.response.CreateProfileResponse
import com.example.user.model.response.InquiryProfileResponse
import com.example.user.model.response.UpdateProfileResponse
import com.example.user.service.ProfileService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/user/profile")
class ProfileController(
    private val profileService: ProfileService
) {

    @GetMapping(
        value = ["/inquiry"]
    )
    @ApiOperation(value = "inquiry user profile")
    @ApiResponses(
        ApiResponse(code = 200, message = "Success.")
    )
    fun inquiry(
        @RequestParam(value = "username") username: String,
        @RequestParam(value = "cid") cid: String
    ): BaseResponse<InquiryProfileResponse> {
        return BaseResponse.Builder<InquiryProfileResponse>().success(
            profileService.inquiry(username, cid)
        )
    }

    @PostMapping(
        value = ["/create"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ApiOperation(value = "create new user profile")
    @ApiResponses(
        ApiResponse(code = 200, message = "Success.")
    )
    fun createUser(@Valid @RequestBody req: CreateProfileRequest): BaseResponse<CreateProfileResponse> =
        BaseResponse.Builder<CreateProfileResponse>().success(profileService.create(req))

    @PostMapping(
        value = ["/update"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ApiOperation(value = "update existing user profile")
    @ApiResponses(
        ApiResponse(code = 200, message = "Success.")
    )
    fun updateUser(@RequestBody req: UpdateProfileRequest): BaseResponse<UpdateProfileResponse> =
        BaseResponse.Builder<UpdateProfileResponse>().success(profileService.update(req))

}
