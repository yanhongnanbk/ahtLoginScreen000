package com.yan.ahtloginscreen000.repositories

import android.util.Log
import androidx.preference.PreferenceManager
import com.yan.ahtloginscreen000.MainApplication
import com.yan.ahtloginscreen000.database.InfoDatabase
import com.yan.ahtloginscreen000.models.Info
import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.models.LoginResponse
import com.yan.ahtloginscreen000.remote.UserApiInterface
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "UserRepository"
private val PREF_NAME = "UserInfo"

class UserRepository(private val userApiInterface: UserApiInterface) {

    val db = InfoDatabase.getInstance(MainApplication.applicationContext())
    val infoDao = db.infoDao()
    val sharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(MainApplication.applicationContext()).edit()


    fun createUser(xAcc: String, login: LoginRequest, onResult: (LoginResponse?) -> Unit) {
        userApiInterface.createUser(xAcc, login).enqueue(
            object : Callback<LoginResponse> {

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure")
                    onResult(null)
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
//                    Log.d(TAG, "Content-Type: ${response.headers()["Content-Type"]}")
//                    Log.d(TAG, "Content-Length: ${response.headers()["Content-Length"]}")
//                    Log.d(TAG, "X-Acc: ${response.headers()["X-Acc"]}")
                    Log.d(TAG, "${response.body()?.toString()}")
                    sharedPreferences.putString(PREF_NAME, response.headers()["X-Acc"])
                    sharedPreferences.commit()

                    GlobalScope.launch {

                        response.headers()["X-Acc"]?.let {
                            response.body()?.user?.let { it1 ->
                                Info(
                                    it,
                                    it1
                                )
                            }
                        }?.let {
                            infoDao.insertLoginInfoResponse(
                                it
                            )
                        }
                    }
                    onResult(response.body())
                }
            }
        )
    }

//    fun getInfo() = infoDao.getInfo()


}

