package com.yan.ahtloginscreen000.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("errorCode")
    val error: String,
    @SerializedName("errorMessage")
    val message:String,
    @SerializedName("user")
    val user: User
)