package com.yan.ahtloginscreen000.views

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.yan.ahtloginscreen000.MainApplication
import com.yan.ahtloginscreen000.R
import com.yan.ahtloginscreen000.data.database.InfoDatabase
import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.data.remote.UserApiInterface
import com.yan.ahtloginscreen000.repositories.UserRepository
import com.yan.ahtloginscreen000.utils.Constants.USER_INFO
import com.yan.ahtloginscreen000.utils.Constants.XACC_INFO
import com.yan.ahtloginscreen000.utils.Helper
import com.yan.ahtloginscreen000.viewmodels.LoginActivityViewModel
import kotlinx.android.synthetic.main.activity_login.*
import java.io.Serializable

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {
    private val loginActivityViewModel by viewModels<LoginActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupViewModels()
        /**Block Click LoginButton*/

        button_login.setOnClickListener {
            val username = textInputEditTextUsername.text.toString()
            val password = textInputEditTextPassword.text.toString()
            textInputEditTextUsername.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    //
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    textInputLayoutUsername.error = null

                }

                override fun afterTextChanged(s: Editable?) {
                    //
                }
            })

            textInputEditTextPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    textInputLayoutPassword.error = null
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            if (!Helper.validateUsername(username)) {
                textInputLayoutUsername.error =
                    "Username can contain only alphanumeric characters, cannot be empty, maximum of 30 chars"
                return@setOnClickListener
            }

            if (!Helper.validatePassword(password)) {
                textInputLayoutPassword.error =
                    "Password can contain only alphanumeric characters, cannot be empty, maximum of 16 chars"
                return@setOnClickListener
            }

            val user = LoginRequest(username = username, password = password)

            loginActivityViewModel.loginUser(user) { loginRequest ->

                Log.d(TAG, "$loginRequest")
                val contentIntent = Intent(applicationContext, SecondActivity::class.java)
                contentIntent.putExtra(USER_INFO, loginRequest.user as Serializable)
                contentIntent.putExtra(XACC_INFO, "x")
                startActivity(contentIntent)

            }

            loginActivityViewModel.showLoading.observe(this, Observer {
                progressbar_login_activity.visibility = if (it) View.VISIBLE else View.GONE
            })
        }
        /**End Block Click LoginButton*/
    }

    /**SetupViewmodels*/
    private fun setupViewModels() {
        val service = UserApiInterface.instance
        val db = InfoDatabase.getInstance(MainApplication.applicationContext())
        val infoDao = db.infoDao()
        loginActivityViewModel.userRepository = UserRepository(service, infoDao)
    }


}

