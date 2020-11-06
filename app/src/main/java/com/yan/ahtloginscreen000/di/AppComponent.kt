package com.yan.ahtloginscreen000.di

import com.yan.ahtloginscreen000.views.LoginActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    // Where u want to inject < A bridge between Module and blah blah>
    fun inject(loginActivity: LoginActivity)

}