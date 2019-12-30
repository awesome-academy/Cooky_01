package com.example.cooky.di

import com.example.cooky.data.remote.AuthInterceptor
import com.example.cooky.data.remote.NetworkConnectionInterceptor
import com.example.cooky.data.remote.ResponseHandler
import com.example.cooky.data.remote.api.BASE_URL
import com.example.cooky.data.remote.api.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { AuthInterceptor() }
    single { createLoggingInterceptor() }
    single { NetworkConnectionInterceptor(get()) }
    single { createOkHttpClient(get(), get(), get()) }
    single { createRetrofit(get()) }
    single { createApiService(get()) }
    single { ResponseHandler() }
}

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun createOkHttpClient(
    authInterceptor: AuthInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
    networkConnectionInterceptor: NetworkConnectionInterceptor
): OkHttpClient = OkHttpClient().newBuilder()
    .addInterceptor(authInterceptor)
    .addInterceptor(loggingInterceptor)
    .addInterceptor(networkConnectionInterceptor)
    .build()

fun createLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BASIC
    return logger
}

fun createApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
