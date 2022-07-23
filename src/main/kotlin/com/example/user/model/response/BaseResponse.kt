package com.example.user.model.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
class BaseResponse<T>(
    var code: String,
    var message: String,
    var data: T? = null,
    var cause: String? = null,
    var remark: String? = null
) {

    class Builder<T>() {
        fun success(data: T?): BaseResponse<T> {
            return BaseResponse("000", "SUCCESS", data, null, null)
        }

        fun error(code: String, message: String, data: T? = null, cause: String? = null, remark: String? = null): BaseResponse<T> {
            return BaseResponse(code, message, data, cause, remark)
        }
    }
}
