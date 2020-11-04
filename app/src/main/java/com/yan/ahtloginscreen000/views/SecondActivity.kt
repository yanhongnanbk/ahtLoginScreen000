package com.yan.ahtloginscreen000.views

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yan.ahtloginscreen000.MainApplication
import com.yan.ahtloginscreen000.R
import com.yan.ahtloginscreen000.database.InfoDatabase
import com.yan.ahtloginscreen000.models.UserInfo
import com.yan.ahtloginscreen000.models.User
import com.yan.ahtloginscreen000.remote.UserApiInterface
import com.yan.ahtloginscreen000.repositories.UserRepository
import com.yan.ahtloginscreen000.utils.Constants.USER_INFO
import com.yan.ahtloginscreen000.utils.Constants.XACC_INFO
import com.yan.ahtloginscreen000.viewmodels.SecondActivityViewModel

private const val TAG = "SecondActivity"

class SecondActivity : AppCompatActivity() {
    private val secondActivityViewModel by viewModels<SecondActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        /**SetupViewmodels*/
        setupViewModels()

        /**Get Intent from Login Screen*/
        val user: User = intent.getSerializableExtra(USER_INFO) as User

        val xAcc = intent.getStringExtra(XACC_INFO).toString()

        Log.d(TAG, "xAcc: $xAcc userInfo: ${user.toString()}")
        /**End getIntent*/

        /**save data to RoomDb*/
        val infoList = secondActivityViewModel.loadInfoList()?.value
        Log.d(TAG, "infoList: ${infoList.toString()}")
//        secondActivityViewModel.saveInfoToRoom(UserInfo(xAcc, user))
        secondActivityViewModel.loadInfoList()?.observe(this, {
            for (x in it) {
                if (user.userId != x.user.userId) {
                    secondActivityViewModel.saveInfoToRoom(UserInfo(xAcc, user))
                }
            }
        })
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