package com.yan.ahtloginscreen000.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpSupport {

    /**
     * OkHttpClient
     */
    fun getOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        interceptor.level = HttpLoggingInterceptor.Level.NONE

        val builder = OkHttpClient.Builder()
        builder
//            .connectTimeout(20, TimeUnit.SECONDS)
//            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(TokenInterceptor())
            .addInterceptor(loggingInterceptor)

        return builder.build()
    }
}