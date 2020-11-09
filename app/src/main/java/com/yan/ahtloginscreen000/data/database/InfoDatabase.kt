package com.yan.ahtloginscreen000.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yan.ahtloginscreen000.models.UserInfo

@Database(
    entities = arrayOf(UserInfo::class),
    version = 5,
    exportSchema = false

)

abstract class InfoDatabase : RoomDatabase() {
    abstract fun infoDao(): InfoDAO
}

