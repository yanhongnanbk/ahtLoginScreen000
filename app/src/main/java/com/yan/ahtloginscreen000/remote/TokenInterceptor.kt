package com.yan.ahtloginscreen000.remote

import android.content.Context
import android.util.Log
import androidx.preference.PreferenceManager
import com.yan.ahtloginscreen000.MainApplication
import okhttp3.Interceptor
import okhttp3.Response

private val PREF_NAME = "UserInfo"

class TokenInterceptor() : Interceptor {

    val sharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(MainApplication.applicationContext())
    val x: String? = sharedPreferences.getString(PREF_NAME, "")

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
            .newBuilder()
            .addHeader("x-Acc", x!!)
            .build()
        return chain.proceed(request)
    }
}