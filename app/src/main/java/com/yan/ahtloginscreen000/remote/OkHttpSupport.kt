package com.yan.ahtloginscreen000.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpSupport {

    /**
     * OkHttpClient
     */
    fun getOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        interceptor.level = HttpLoggingInterceptor.Level.NONE

        val builder = OkHttpClient.Builder()
        builder
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(SupportInterceptor())
            .addInterceptor(interceptor)

        return builder.build()
    }
}