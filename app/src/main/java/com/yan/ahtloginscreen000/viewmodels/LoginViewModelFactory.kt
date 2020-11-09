package com.yan.ahtloginscreen000.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory @Inject constructor(private val loginViewModelProvider: Provider<LoginActivityViewModel>) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return loginViewModelProvider.get() as T
    }

}