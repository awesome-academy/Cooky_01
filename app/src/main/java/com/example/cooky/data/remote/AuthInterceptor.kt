package com.example.cooky.data.remote

import com.example.cooky.BuildConfig
import com.example.cooky.data.remote.api.TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(TOKEN, BuildConfig.API_KEY)
            .build()
        request = request.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }
}
