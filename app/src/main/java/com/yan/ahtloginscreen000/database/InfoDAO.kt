package com.yan.ahtloginscreen000.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.yan.ahtloginscreen000.models.Info
import com.yan.ahtloginscreen000.models.LoginResponse

@Dao
interface InfoDAO {

    // 4
    @Insert(onConflict = REPLACE)
    fun insertLoginInfoResponse(info: Info)

//    @Query("SELECT * from Info ORDER BY id ASC")
//    fun getInfo() : LiveData<List<Info>>

}