package com.yan.ahtloginscreen000.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.models.LoginResponse
import com.yan.ahtloginscreen000.repositories.UserRepository

private const val TAG = "LoginActivityViewModel"

class LoginActivityViewModel(application: Application) : AndroidViewModel(application) {

    var userRepository: UserRepository? = null

    // Unit similar to void in java
    fun loginUser(login: LoginRequest, callback: (LoginResponse,String?) -> Unit) {
        userRepository?.loginUser(login) { results, xAcc ->
            Log.d(TAG,xAcc!!)
            if (results == null) {
                Log.d(TAG, "Error")
            } else if (results.error == "01") {
                Log.d(TAG, "ErrorCode = ${results.error}")
                callback(results,xAcc)
            } else {
                Log.d(TAG, "ErrorCode = ${results.error}")
            }
        }
    }

}