package com.example.freeapp.data.remote

import com.example.freeapp.data.remote.dto.auth.ApiErrorResponse
import com.example.freeapp.data.remote.dto.auth.AuthenticationResponse
import com.example.freeapp.data.remote.dto.auth.LoginRequest
import com.example.freeapp.data.remote.dto.auth.RegisterRequest
import com.example.freeapp.data.remote.dto.auth.ResetPasswordRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthenticationResponse

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest): AuthenticationResponse

    @POST("auth/reset-password")
    suspend fun resetPassword(
        @Body request: ResetPasswordRequest): AuthenticationResponse
}