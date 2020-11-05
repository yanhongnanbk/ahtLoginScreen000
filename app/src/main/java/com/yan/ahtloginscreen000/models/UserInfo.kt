package com.yan.ahtloginscreen000.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfo(
    var xAcc: String = "",
    var userId: String = "",
    var userName: String = "",
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}