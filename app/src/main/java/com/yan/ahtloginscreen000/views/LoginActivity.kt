package com.yan.ahtloginscreen000.views

import android.content.Intent
import android.os.Bundle
import android.view.View
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

private const val TAG = "LoginActivity"
class LoginActivity : AppCompatActivity() {
    private val loginActivityViewModel by viewModels<LoginActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViewModels()

        button_login.setOnClickListener {
            val username = textInputEditTextUsername.text.toString()
            val password = textInputEditTextPassword.text.toString()
            if (!Helper.validateUsername(username)){
                textInputLayoutUsername.error = "Username can contain only alphanumeric characters, cannot be empty, maximum of 30 chars"
                return@setOnClickListener
            }
            if (!Helper.validatePassword(password)){
                textInputLayoutPassword.error = "Password can contain only alphanumeric characters, cannot be empty, maximum of 16 chars"
                return@setOnClickListener
            }
            textInputLayoutPassword.error = null
            textInputLayoutUsername.error = null
            val user = LoginRequest(username = username, password = password)
            loginActivityViewModel.createUser(user) {
                    startActivity(Intent(this, SecondActivity::class.java))
            }

        }

    }

    /**SetupViewmodels*/
    private fun setupViewModels() {
        val service = UserApiInterface.instance
        loginActivityViewModel.userRepository = UserRepository(service, this)
    }


}
