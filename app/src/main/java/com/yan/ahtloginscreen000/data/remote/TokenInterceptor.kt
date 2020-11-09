package com.yan.ahtloginscreen000.data.remote

import com.yan.ahtloginscreen000.repositories.UserManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(val userManager: UserManager) : Interceptor {

    /**
     * Interceptor class for setting of the headers for every request
     */
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
            .addHeader("x-Acc", userManager.userAcc)
            .build()
        return chain.proceed(request)
    }
}