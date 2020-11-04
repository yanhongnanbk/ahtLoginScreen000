package com.yan.ahtloginscreen000.views

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.yan.ahtloginscreen000.MainApplication
import com.yan.ahtloginscreen000.R
import com.yan.ahtloginscreen000.database.InfoDatabase
import com.yan.ahtloginscreen000.models.Info
import com.yan.ahtloginscreen000.models.User
import com.yan.ahtloginscreen000.remote.UserApiInterface
import com.yan.ahtloginscreen000.repositories.UserRepository
import com.yan.ahtloginscreen000.utils.Constants
import com.yan.ahtloginscreen000.utils.Constants.USER_INFO
import com.yan.ahtloginscreen000.utils.Constants.XACC_INFO
import com.yan.ahtloginscreen000.viewmodels.SecondActivityViewModel

private const val TAG = "SecondActivity"

class SecondActivity : AppCompatActivity() {
    private val secondActivityViewModel by viewModels<SecondActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        setupViewModels()

        /**Get Intent from Login Screen*/
        val user = intent.getSerializableExtra(USER_INFO)

        val xAcc = intent.getStringExtra(XACC_INFO).toString()

        Log.d(TAG, "xAcc: $xAcc userInfo: ${user.toString()}")
        /**End getIntent*/

        /**save data to RoomDb*/
        secondActivityViewModel.saveInfoToRoom(Info(xAcc, user as User))
        /**End save data to RoomDb*/

    }

    /**SetupViewmodels*/
    private fun setupViewModels() {
        val service = UserApiInterface.instance
        val db = InfoDatabase.getInstance(MainApplication.applicationContext())
        val infoDao = db.infoDao()
        secondActivityViewModel.userRepository = UserRepository(service, infoDao)
    }
}