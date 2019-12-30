package com.example.cooky.data.remote

import android.content.Context
import android.net.ConnectivityManager
import com.example.cooky.MainApplication
import com.example.cooky.R
import com.example.cooky.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetConnected())
            throw NoInternetException(MainApplication.applicationContext!!.getString(R.string.message_no_internet))
        return chain.proceed(chain.request())
    }

    private fun isInternetConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}
