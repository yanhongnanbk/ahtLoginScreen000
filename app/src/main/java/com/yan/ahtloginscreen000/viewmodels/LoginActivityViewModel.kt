package com.yan.ahtloginscreen000.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.yan.ahtloginscreen000.models.Info
import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.models.LoginResponse
import com.yan.ahtloginscreen000.repositories.UserRepository

private const val TAG = "LoginActivityViewModel"
class LoginActivityViewModel(application: Application) : AndroidViewModel(application) {

    var userRepository: UserRepository? = null

    // Unit similar to void in java
    fun createUser(login: LoginRequest, callback: (LoginResponse) -> Unit) {
        userRepository?.createUser(login) { results ->
            if (results == null) {
                Log.d(TAG,"Error")
            } else {
                callback(results)
            }
        }
    }

}