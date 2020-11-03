package com.yan.ahtloginscreen000.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class SupportInterceptor: Interceptor {

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("IMSI", "357175048449937")
            .addHeader("IMEI", "510110406068589")
            .build()
        return chain.proceed(request)
    }
}