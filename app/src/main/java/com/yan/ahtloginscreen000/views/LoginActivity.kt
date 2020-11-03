package com.yan.ahtloginscreen000.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.yan.ahtloginscreen000.R
import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.remote.UserApiInterface
import com.yan.ahtloginscreen000.repositories.UserRepository
import kotlinx.android.synthetic.main.activity_login.*
import com.yan.ahtloginscreen000.utils.Helper
import com.yan.ahtloginscreen000.viewmodels.LoginActivityViewModel

class LoginActivity : AppCompatActivity() {
    private val loginActivityViewModel by viewModels<LoginActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViewModels()

        button_login.setOnClickListener {
            val username = editText_username.text.toString()
            val password = editText_password.text.toString()
            val user = LoginRequest(username = username, password = password)
            if (!Helper.validateUsername(username) || !Helper.validatePassword(password)) {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            } else {
                loginActivityViewModel.createUser(user) {
//                    startActivity(Intent(this, SecondActivity::class.java))
                }
//
            }
        }

    }

    /**SetupViewmodels*/
    private fun setupViewModels() {
        val service = UserApiInterface.instance
        loginActivityViewModel.userRepository = UserRepository(service, this)
    }

}
