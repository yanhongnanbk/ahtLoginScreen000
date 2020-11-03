package com.yan.ahtloginscreen000.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Info(
    var xAcc: String = "",
    var user: User

){
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}