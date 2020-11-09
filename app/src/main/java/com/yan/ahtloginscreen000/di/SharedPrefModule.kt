package com.yan.ahtloginscreen000.di

import com.yan.ahtloginscreen000.data.sharedPref.SharedPrefInterface
import com.yan.ahtloginscreen000.data.sharedPref.SharedPreferencesStorage
import dagger.Binds
import dagger.Module

@Module
abstract class SharedPrefModule {

    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): SharedPrefInterface

}