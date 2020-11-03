package com.yan.ahtloginscreen000.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.yan.ahtloginscreen000.models.Info
import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.models.LoginResponse
import com.yan.ahtloginscreen000.repositories.UserRepository

private const val TAG = "SecondActivityViewModel"
class SecondActivityViewModel(application: Application) : AndroidViewModel(application) {

    var userRepository: UserRepository? = null

//    fun saveInfoToRoom(info:Info) {
//        userRepository?.saveInfoToRoom(info)
//    }


}