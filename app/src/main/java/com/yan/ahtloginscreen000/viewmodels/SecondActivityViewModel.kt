package com.yan.ahtloginscreen000.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.yan.ahtloginscreen000.models.UserInfo
import com.yan.ahtloginscreen000.repositories.UserRepository

private const val TAG = "SecondActivityViewModel"

class SecondActivityViewModel(application: Application) : AndroidViewModel(application) {

    var userRepository: UserRepository? = null
    var liveUserInfoData: LiveData<List<UserInfo>>? = null

    fun saveInfoToRoom(userInfo: UserInfo) {
        userRepository?.saveInfoToRoom(userInfo)
    }

    fun loadInfoList(): LiveData<List<UserInfo>>? {
        if (liveUserInfoData == null) {
            liveUserInfoData = userRepository?.loadInfoList()
        }
        return liveUserInfoData
    }

}