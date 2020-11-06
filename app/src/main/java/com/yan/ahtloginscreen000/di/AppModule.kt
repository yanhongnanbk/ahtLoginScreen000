package com.yan.ahtloginscreen000.di

import com.yan.ahtloginscreen000.data.remote.ApiService
import com.yan.ahtloginscreen000.data.remote.UserApiInterface
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApi(): UserApiInterface = ApiService.getClient()

}