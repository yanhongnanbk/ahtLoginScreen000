package com.yan.ahtloginscreen000.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yan.ahtloginscreen000.models.UserInfo
import com.yan.ahtloginscreen000.repositories.UserRepository

private const val TAG = "SecondActivityViewModel"

class SecondActivityViewModel(application: Application) : AndroidViewModel(application) {
    val progressVisible = MutableLiveData<Boolean>()
    var userRepository: UserRepository? = null
    var liveUserInfoData: LiveData<List<UserInfo>>? = null

//    fun saveInfoToRoom(userInfo: UserInfo) {
//        userRepository?.saveInfoToRoom(userInfo)
//    }
//
//    fun loadInfoList(): LiveData<List<UserInfo>>? {
//        progressVisible.value = true
//        if (liveUserInfoData == null) {
//            liveUserInfoData = userRepository?.loadInfoList()
//        }
//        return liveUserInfoData
//    }

}