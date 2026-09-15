package com.example.freeapp.data.remote.dto.auth

data class AuthenticationResponse(
    val success: Boolean = true,
    val message: String = "",
    val token: String? = null
)