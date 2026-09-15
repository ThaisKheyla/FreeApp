package com.example.freeapp.data.remote.dto.auth

data class ResetPasswordRequest(
    val email: String,
    val newPassword: String
)