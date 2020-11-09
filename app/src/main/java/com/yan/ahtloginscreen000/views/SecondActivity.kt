package com.yan.ahtloginscreen000.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yan.ahtloginscreen000.R
import com.yan.ahtloginscreen000.models.User
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
//        setupViewModels()

        /**Get Intent from Login Screen*/
        val user: User = intent.getSerializableExtra(USER_INFO) as User

        textViewSecondActivityUserId.text = "UserId: "+ user.userId
        textViewSecondActivityUsername.text = "Username: "+ user.userName
        /**End getIntent*/

        /**End save data to RoomDb*/
    }


//    /**SetupViewmodels*/
//    private fun setupViewModels() {
//        val service = ApiService.getClient()
//        val db = InfoDatabase.getInstance(MainApplication.applicationContext())
//        val infoDao = db.infoDao()
//        secondActivityViewModel.userRepository = UserRepository(service, infoDao)
//    }
}