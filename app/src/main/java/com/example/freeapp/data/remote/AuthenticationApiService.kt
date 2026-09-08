package com.example.freeapp.data.remote

import com.example.freeapp.data.remote.dto.AuthenticationResponse
import com.example.freeapp.data.remote.dto.LoginRequest
import com.example.freeapp.data.remote.dto.RegisterRequest
import com.example.freeapp.data.remote.dto.ResetPasswordRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApiService {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthenticationResponse

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): AuthenticationResponse

    @POST("auth/reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): AuthenticationResponse
}
