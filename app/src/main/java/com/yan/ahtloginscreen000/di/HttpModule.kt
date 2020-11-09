package com.yan.ahtloginscreen000.di

import com.amitshekhar.debug.BuildConfig
import com.yan.ahtloginscreen000.data.remote.TokenInterceptor
import com.yan.ahtloginscreen000.data.remote.UserApiInterface
import com.yan.ahtloginscreen000.repositories.UserManager
import com.yan.ahtloginscreen000.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
open class HttpModule {

    @Provides
    @Singleton
    open fun provideHttpLogging(userManager: UserManager): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.HEADERS
        return OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(userManager))
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    open fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    open fun provideApiService(retrofit: Retrofit): UserApiInterface = retrofit.create(UserApiInterface::class.java)

}