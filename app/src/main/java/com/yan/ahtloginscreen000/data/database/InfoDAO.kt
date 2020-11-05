package com.yan.ahtloginscreen000.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.yan.ahtloginscreen000.models.UserInfo

@Dao
interface InfoDAO {

    @Insert(onConflict = REPLACE)
    fun insertLoginInfoResponse(userInfo: UserInfo)

//    @Query("SELECT * FROM UserInfo ")
//    fun loadInfoList(): LiveData<List<UserInfo>>

    @Query("SELECT * FROM UserInfo WHERE userId = :userId ")
    fun loadInfoWithUserId(userId: String): UserInfo

    @Query("UPDATE UserInfo SET xAcc =:xAcc")
    fun updateXAcc(xAcc: String)
}