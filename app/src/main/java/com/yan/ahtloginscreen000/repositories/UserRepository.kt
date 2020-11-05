package com.yan.ahtloginscreen000.repositories

import android.util.Log
import androidx.preference.PreferenceManager
import com.yan.ahtloginscreen000.MainApplication
import com.yan.ahtloginscreen000.data.database.InfoDAO
import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.models.LoginResponse
import com.yan.ahtloginscreen000.models.UserInfo
import com.yan.ahtloginscreen000.data.remote.UserApiInterface
import com.yan.ahtloginscreen000.utils.Constants.PREF_NAME
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "UserRepository"

class UserRepository(private val userApiInterface: UserApiInterface, private var infoDAO: InfoDAO) {

    val sharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(MainApplication.applicationContext()).edit()

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

                    GlobalScope.launch {

                        sharedPreferences.putString(PREF_NAME, response.headers()["X-Acc"])
                        sharedPreferences.commit()

                        val xAcc = response.headers()["X-Acc"]
                        val userId = response.body()?.user?.userId
                        val userName = response.body()?.user?.userName

                        val x = infoDAO.loadInfoList()
                        if (!x.any { it.userId==userId }){
                            val userInfo = UserInfo(xAcc.toString(),userId.toString(),userName.toString())
                            infoDAO.insertLoginInfoResponse(userInfo)
                        }
//                        else{
//                            if (userId != null) {
//                                infoDAO.updateUserInfo(userId)
//                            }
//                        }

                        onResult(response.body())
                    }


                }
            }
        )
    }


//    fun saveInfoToRoom(userInfo: UserInfo) {
//        GlobalScope.launch {
//            infoDAO.insertLoginInfoResponse(userInfo)
//        }
//    }

//    fun loadInfoList(): LiveData<List<UserInfo>> {
//        return infoDAO.loadInfoList()
//    }

}

