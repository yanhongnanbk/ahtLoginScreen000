package com.yan.ahtloginscreen000.repositories

import com.yan.ahtloginscreen000.data.sharedPref.SharedPreferencesStorage
import com.yan.ahtloginscreen000.utils.Constants.PREF_NAME
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserManager @Inject constructor(val storage: SharedPreferencesStorage) {

    val userAcc: String
        get() = storage.getString(PREF_NAME)

    fun saveXacc(userAcc:String){
        storage.setString(PREF_NAME, userAcc)
    }
}