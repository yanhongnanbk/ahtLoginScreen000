package com.yan.ahtloginscreen000.data.remote

import androidx.preference.PreferenceManager
import com.yan.ahtloginscreen000.MainApplication
import com.yan.ahtloginscreen000.utils.Constants.PREF_NAME
import okhttp3.Interceptor
import okhttp3.Response

private const val TAG = "TokenInterceptor"

class TokenInterceptor() : Interceptor {



    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {

        val sharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(MainApplication.applicationContext())
        val x: String? = sharedPreferences.getString(PREF_NAME, "")

        val request = chain.request()
            .newBuilder()
            .addHeader("x-Acc", x!!)
            .build()
        return chain.proceed(request)
    }
}