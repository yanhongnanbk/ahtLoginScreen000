package com.yan.ahtloginscreen000.di

import androidx.lifecycle.ViewModelProvider
import com.yan.ahtloginscreen000.viewmodels.LoginViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun provideViewModelFactory(factory:LoginViewModelFactory): ViewModelProvider.Factory
}