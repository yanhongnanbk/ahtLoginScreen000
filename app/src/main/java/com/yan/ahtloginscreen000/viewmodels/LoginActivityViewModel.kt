package com.yan.ahtloginscreen000.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.models.LoginResponse
import com.yan.ahtloginscreen000.repositories.UserRepository
import javax.inject.Inject

private const val TAG = "LoginActivityViewModel"

class LoginActivityViewModel @Inject constructor(var userRepository: UserRepository): ViewModel() {

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean>
        get() = _showLoading

    // Unit similar to void in java
    fun loginUser(login: LoginRequest, callback: (LoginResponse) -> Unit) {
        _showLoading.postValue(true)
        userRepository?.loginUser(login) { results ->

            when {
                results == null -> {
                    Log.d(TAG, "Error")
                }
                results.error == "01" -> {
                    Log.d(TAG, "ErrorMessage = ${results.message}")
                    callback(results)
                }
                else -> {
                    Log.d(TAG, "ErrorMessage = ${results.message}")
                }
            }
            _showLoading.postValue(false)
        }
    }

}