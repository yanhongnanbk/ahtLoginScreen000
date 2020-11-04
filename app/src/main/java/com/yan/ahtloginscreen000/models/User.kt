package com.yan.ahtloginscreen000.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("userName")
    val userName: String,
):Serializable