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

        val builder = OkHttpClient.Builder()
        builder
            .addInterceptor(TokenInterceptor())
            .addInterceptor(loggingInterceptor)

        return builder.build()
    }
}