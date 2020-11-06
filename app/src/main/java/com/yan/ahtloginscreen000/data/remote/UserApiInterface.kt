package com.yan.ahtloginscreen000.data.remote

import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApiInterface {

    @Headers(
        "Content-Type:application/json",
        "IMSI:357175048449937",
        "IMEI:510110406068589"
    )
    @POST("login")
    fun loginUser(@Body login: LoginRequest): Call<LoginResponse>

}

