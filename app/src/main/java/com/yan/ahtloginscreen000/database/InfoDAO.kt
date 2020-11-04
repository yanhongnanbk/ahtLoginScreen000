package com.yan.ahtloginscreen000.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import com.yan.ahtloginscreen000.models.Info

@Dao
interface InfoDAO {

    @Insert(onConflict = REPLACE)
    fun insertLoginInfoResponse(info: Info)

}