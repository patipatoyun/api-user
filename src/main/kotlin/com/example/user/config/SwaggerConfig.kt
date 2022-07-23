package com.example.user.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.ParameterBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType.SWAGGER_2
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.UUID

@Configuration
@EnableSwagger2
class SwaggerConfig {
    val GROUP_NAME: String = "USER"

    @Bean
    fun api(): Docket {
        return Docket(SWAGGER_2)
            .globalOperationParameters(
                listOf(
                    createHeader("Accept-Language", "en"),
                    createHeader("X-Brand", "swagger-brand"),
                    createHeader("X-Model", "swagger-model"),
                    createHeader("X-App-Version", "2.0"),
                    createHeader("X-Platform", "swagger-platform"),
                    createHeader("x-Device-ID", UUID.randomUUID().toString())
                )
            )
            .groupName(GROUP_NAME)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.example.user"))
            .build()
    }

    private fun createHeader(name: String, value: String = "") =
        ParameterBuilder().name(name).modelRef(ModelRef("string"))
            .parameterType("header").required(true).defaultValue(value).build()

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder().title("User Information API")
            .description("User Service")
            .version("1.0")
            .build()
    }
}