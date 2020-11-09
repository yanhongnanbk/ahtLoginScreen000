package com.yan.ahtloginscreen000.di

import android.content.Context
import com.yan.ahtloginscreen000.data.remote.TokenInterceptor
import com.yan.ahtloginscreen000.views.LoginActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
//@Component(modules = [NetworkModule::class, StorageModule::class, ViewModelModule::class,SharedPrefModule::class,HttpModule::class])
@Component(modules = [StorageModule::class, ViewModelModule::class,SharedPrefModule::class,HttpModule::class])
interface AppComponent {

    // Where u want to inject < A bridge between Module and Fragment/Activity...>
    fun inject(loginActivity: LoginActivity)
    fun inject(tokenInterceptor: TokenInterceptor)

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }
}