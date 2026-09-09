package com.example.freeapp.data.remote

import com.example.freeapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private val loggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val httpClient =
        OkHttpClient.Builder()
            .connectTimeout(
                30,
                TimeUnit.SECONDS
            )
            .readTimeout(
                30,
                TimeUnit.SECONDS
            )
            .writeTimeout(
                30,
                TimeUnit.SECONDS
            )
            .addInterceptor(
                loggingInterceptor
            )
            .build()

    private val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl(
                BuildConfig.API_BASE_URL
            )
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .client(
                httpClient
            )
            .build()
    }

    val authenticationApiService:
            AuthenticationApiService by lazy {

        retrofit.create(
            AuthenticationApiService::class.java
        )
    }
}