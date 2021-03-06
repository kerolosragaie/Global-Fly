package com.kerollosragaie.globalfly.services

import android.os.Build
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

object ServiceBuilder {
    //BASE URL
    private const val BASE_URL = "http://10.0.2.2:9000/"

    //create logger:
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    /**
     * Create custom interceptor to apply Headers application wide
     * Note: for every call (CRUD) the interceptor will show headers
     * */
    private val headerInterceptor: Interceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request: Request = chain.request()
            request = request.newBuilder()
                .addHeader("x-device-type", Build.DEVICE)
                .addHeader("Accept-Language", Locale.getDefault().language)
                .build()
            return chain.proceed(request)
        }
    }

    // Create OkHttp Client
    private val okHttp = OkHttpClient.Builder()
        //The normal time out is 10 seconds
        //call == read and write and connect timeouts
        .callTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(headerInterceptor)
        .addInterceptor(logger)


    private val builder= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit:Retrofit = builder.build()

    fun <T> buildService(serviceType:Class<T>):T{
        return retrofit.create(serviceType)
    }

}