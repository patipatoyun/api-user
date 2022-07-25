package com.example.user.client

import com.example.user.config.FeignRandomUserErrorDecoder
import com.example.user.config.FormFeignConfiguration
import com.example.user.model.client.RandomUserResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    value = "randomUserClient",
    url = "\${random-user.url}",
    configuration = [FormFeignConfiguration::class, FeignRandomUserErrorDecoder::class]
)
interface RandomUserClient {

    @GetMapping(
        value = ["/api/"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun inquiry(@RequestParam(value = "seed") keyword: String): RandomUserResponse
}
