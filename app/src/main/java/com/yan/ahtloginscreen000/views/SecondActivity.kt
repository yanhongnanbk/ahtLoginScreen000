package com.yan.ahtloginscreen000.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yan.ahtloginscreen000.MainApplication
import com.yan.ahtloginscreen000.R
import com.yan.ahtloginscreen000.data.database.InfoDatabase
import com.yan.ahtloginscreen000.models.User
import com.yan.ahtloginscreen000.data.remote.UserApiInterface
import com.yan.ahtloginscreen000.repositories.UserRepository
import com.yan.ahtloginscreen000.utils.Constants.USER_INFO
import com.yan.ahtloginscreen000.viewmodels.SecondActivityViewModel
import kotlinx.android.synthetic.main.activity_second.*

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

//        val xAcc = intent.getStringExtra(XACC_INFO).toString()

        textViewSecondActivityUserId.text = "UserId: "+ user.userId
        textViewSecondActivityUsername.text = "Username: "+ user.userName
        /**End getIntent*/

//        secondActivityViewModel.loadInfoList()?.observe(this, {
//
//            if (it!=null){
//                /**save data to RoomDb*/
//                for (x in it) {
//                    if (user.userId != x.user.userId) {
//                        secondActivityViewModel.saveInfoToRoom(UserInfo(xAcc, user))
//                    }
//                }
//            }else{
//                secondActivityViewModel.saveInfoToRoom(UserInfo(xAcc, user))
//            }
////            progressbar_second_activity.visibility = View.GONE
//        })
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