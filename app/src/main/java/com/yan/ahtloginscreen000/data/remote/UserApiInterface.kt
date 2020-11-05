package com.yan.ahtloginscreen000.data.remote

import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.models.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

const val BASE_URL = "https://private-222d3-homework5.apiary-mock.com/api/"

interface UserApiInterface {

    @Headers(
        "Content-Type:application/json",
        "IMSI:357175048449937",
        "IMEI:510110406068589"
    )
    @POST("login")
    fun loginUser(
        @Body login: LoginRequest
    ): Call<LoginResponse>

    companion object {
        val instance: UserApiInterface by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

                .client(OkHttpSupport.getOkHttpClient())
                .build()

            retrofit.create<UserApiInterface>(UserApiInterface::class.java)
        }
    }


}

