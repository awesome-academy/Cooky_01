package com.example.cooky.base

data class BaseResponse<T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T?): BaseResponse<T> =
            BaseResponse(Status.SUCCESS, data, null)

        fun <T> error(msg: String, data: T?): BaseResponse<T> =
            BaseResponse(Status.ERROR, data, msg)

        fun <T> loading(data: T?): BaseResponse<T> =
            BaseResponse(Status.LOADING, data, null)
    }
}
