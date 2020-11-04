package com.yan.ahtloginscreen000.repositories

import android.util.Log
import androidx.preference.PreferenceManager
import com.yan.ahtloginscreen000.MainApplication
import com.yan.ahtloginscreen000.database.InfoDAO
import com.yan.ahtloginscreen000.models.Info
import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.models.LoginResponse
import com.yan.ahtloginscreen000.remote.UserApiInterface
import com.yan.ahtloginscreen000.utils.Constants.PREF_NAME
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "UserRepository"

class UserRepository(private val userApiInterface: UserApiInterface, private var infoDAO: InfoDAO) {

    //    val db = InfoDatabase.getInstance(MainApplication.applicationContext())
//    val infoDao = db.infoDao()
    val sharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(MainApplication.applicationContext()).edit()

    fun loginUser(login: LoginRequest, onResult: (LoginResponse?, String?) -> Unit) {
        userApiInterface.loginUser(login).enqueue(
            object : Callback<LoginResponse> {

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure")
                    onResult(null, "")
                }

                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {

                    Log.d(TAG, "${response.body()?.toString()}")
                    sharedPreferences.putString(PREF_NAME, response.headers()["X-Acc"])
                    sharedPreferences.commit()

                    onResult(response.body(), response.headers()["X-Acc"])
                }
            }
        )
    }



    fun saveInfoToRoom(info: Info) {
        GlobalScope.launch {
            infoDAO.insertLoginInfoResponse(info)
        }
    }

    companion object

}

