package com.yan.ahtloginscreen000.data.remote

import com.yan.ahtloginscreen000.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    /**OkHttpClient*/
    fun getOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val builder = OkHttpClient.Builder()
        builder
            .addInterceptor(TokenInterceptor())
            .addInterceptor(loggingInterceptor)

        return builder.build()
    }
    /***/
    fun getClient(): UserApiInterface {
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<UserApiInterface>(UserApiInterface::class.java)
    }
    /***/

}