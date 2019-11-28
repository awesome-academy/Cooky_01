package com.example.cooky.data.remote

import com.example.cooky.data.remote.api.*
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException

class ResponseHandler() {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> Resource.error(getErrorMessage(CODE_TIMEOUT), null)
            else -> Resource.error(e.toString(), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            CODE_TIMEOUT -> MESSAGE_TIMEOUT
            CODE_UNAUTHORISED -> MESSAGE_UNAUTHORISED
            CODE_NOTFOUND -> MESSAGE_NOTFOUND
            else -> MESSAGE_SOMETHING_ERROR
        }
    }
}
