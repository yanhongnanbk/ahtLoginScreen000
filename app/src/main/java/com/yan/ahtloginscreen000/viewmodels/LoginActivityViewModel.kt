package com.yan.ahtloginscreen000.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.models.LoginResponse
import com.yan.ahtloginscreen000.repositories.UserRepository

private const val TAG = "LoginActivityViewModel"

class LoginActivityViewModel(application: Application) : AndroidViewModel(application) {

    var userRepository: UserRepository? = null
    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading

    // Unit similar to void in java
    fun loginUser(login: LoginRequest, callback: (LoginResponse, String?) -> Unit) {
        _showLoading.value = true
        userRepository?.loginUser(login) { results, xAcc ->

            Log.d(TAG, xAcc!!)
            when {
                results == null -> {
                    Log.d(TAG, "Error")
                }
                results.error == "01" -> {
                    Log.d(TAG, "ErrorMessage = ${results.message}")
                    callback(results, xAcc)
                }
                else -> {
                    Log.d(TAG, "ErrorMessage = ${results.message}")
                }
            }
            _showLoading.value = false
        }
    }

}