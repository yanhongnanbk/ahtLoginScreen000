package com.yan.ahtloginscreen000.repositories

import android.util.Log
import androidx.preference.PreferenceManager
import com.yan.ahtloginscreen000.data.database.InfoDAO
import com.yan.ahtloginscreen000.data.remote.UserApiInterface
import com.yan.ahtloginscreen000.data.sharedPref.SharedPreferencesStorage
import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.models.LoginResponse
import com.yan.ahtloginscreen000.models.UserInfo
import com.yan.ahtloginscreen000.utils.Constants.PREF_NAME
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "UserRepository"

class UserRepository @Inject constructor(val userApiInterface: UserApiInterface, val infoDAO: InfoDAO,val userManager: UserManager) {

    fun loginUser(login: LoginRequest, onResult: (LoginResponse?) -> Unit) {
        userApiInterface.loginUser(login).enqueue(
            object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure")
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    response.headers()["X-Acc"]?.let { userManager.saveXacc(it) }

                    GlobalScope.launch {
                        val xAcc = response.headers()["X-Acc"].toString()
                        val userId = response.body()?.user?.userId.toString()
                        val userName = response.body()?.user?.userName.toString()
                        if (infoDAO.loadInfoWithUserId(userId) == null) {
                            infoDAO.insertLoginInfoResponse(UserInfo(xAcc, userId, userName))
                        } else {
                            infoDAO.updateXAcc(xAcc)
                        }

                        onResult(response.body())
                    }


                }
            }
        )
    }

}

