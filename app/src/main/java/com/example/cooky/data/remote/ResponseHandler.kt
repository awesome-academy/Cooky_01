package com.example.cooky.data.remote

import com.example.cooky.MainApplication
import com.example.cooky.R
import com.example.cooky.base.BaseResponse
import com.example.cooky.data.remote.api.CODE_NOTFOUND
import com.example.cooky.data.remote.api.CODE_NO_INTERNET
import com.example.cooky.data.remote.api.CODE_TIMEOUT
import com.example.cooky.data.remote.api.CODE_UNAUTHORISED
import com.example.cooky.util.NoInternetException
import retrofit2.HttpException
import java.net.SocketTimeoutException

class ResponseHandler() {
    fun <T : Any> handleSuccess(data: T): BaseResponse<T> {
        return BaseResponse.success(data)
    }

    fun <T : Any> handleException(e: Exception): BaseResponse<T> {
        return when (e) {
            is HttpException -> BaseResponse.error(getErrorMessage(e.code()), null)
            is SocketTimeoutException -> BaseResponse.error(getErrorMessage(CODE_TIMEOUT), null)
            is NoInternetException -> BaseResponse.error(getErrorMessage(CODE_NO_INTERNET), null)
            else -> BaseResponse.error(e.toString(), null)
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            CODE_TIMEOUT -> MainApplication.applicationContext!!.getString(R.string.message_timeout)
            CODE_UNAUTHORISED -> MainApplication.applicationContext!!.getString(R.string.message_authorized)
            CODE_NOTFOUND -> MainApplication.applicationContext!!.getString(R.string.message_not_found)
            CODE_NO_INTERNET -> MainApplication.applicationContext!!.getString(R.string.message_no_internet)
            else -> MainApplication.applicationContext!!.getString(R.string.message_something_error)
        }
    }
}
