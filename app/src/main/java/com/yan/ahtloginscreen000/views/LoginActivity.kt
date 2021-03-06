package com.yan.ahtloginscreen000.views

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yan.ahtloginscreen000.MyApplication
import com.yan.ahtloginscreen000.R
import com.yan.ahtloginscreen000.models.LoginRequest
import com.yan.ahtloginscreen000.utils.Constants.USER_INFO
import com.yan.ahtloginscreen000.utils.Constants.XACC_INFO
import com.yan.ahtloginscreen000.utils.Helper
import com.yan.ahtloginscreen000.viewmodels.LoginActivityViewModel
import kotlinx.android.synthetic.main.activity_login.*
import java.io.Serializable
import javax.inject.Inject

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var loginActivityViewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        /**Dagger*/
        (application as MyApplication).appComponent.inject(this)
        /**End Dagger*/
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginActivityViewModel = ViewModelProvider(this,factory).get(LoginActivityViewModel::class.java)
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

}

