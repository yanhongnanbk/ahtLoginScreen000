package com.yan.ahtloginscreen000.remote

import com.yan.ahtloginscreen000.models.LoginResponse
import com.yan.ahtloginscreen000.models.LoginRequest
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

const val BASE_URL = "https://private-222d3-homework5.apiary-mock.com/api/"

interface UserApiInterface {

    @Headers("x: y","z:t")
    @POST("login")
    fun createUser(
        @Header("Token") token: String?,
        @Body login: LoginRequest
    ): Call<LoginResponse>

    companion object {
        val instance: UserApiInterface by lazy {
            /***/
//            val interceptor = HttpLoggingInterceptor()
//            interceptor.level = HttpLoggingInterceptor.Level.HEADERS
//            val httpClient = OkHttpClient.Builder().apply { addInterceptor(interceptor) }
//            httpClient.addInterceptor { chain ->
//                chain.proceed(chain.request().newBuilder().addHeader("x", "y").build())
//            }
            /***/
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
//                .client(OkHttpClient.Builder().apply { addInterceptor(SupportInterceptor()) }.build())
//                .client(httpClient.addInterceptor { chain ->
//                    chain.proceed(chain.request().newBuilder().addHeader("x", "y").build())
//                }.build())
                .client(OkHttpSupport.getOkHttpClient())
                .build()

            retrofit.create<UserApiInterface>(UserApiInterface::class.java)
        }
    }


}

