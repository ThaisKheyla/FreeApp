package com.example.freeapp.data.remote.dto

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterRequest(
    val name: String,
    val birthDate: String,
    val cpf: String,
    val email: String,
    val phone: String,
    val password: String,
    val zipCode: String,
    val address: String,
    val number: String,
    val complement: String,
    val district: String,
    val city: String,
    val state: String,
    val profession: String,
    val specialty: String,
    val region: String,
    val schedule: String,
    val agency: String,
    val account: String,
    val accountType: String,
    val pix: String,
    val paymentMethod: String,
    val cardNumber: String,
    val cardExpiration: String,
    val cvv: String
)

data class ResetPasswordRequest(
    val email: String,
    val newPassword: String
)

data class AuthenticationResponse(
    val success: Boolean = true,
    val message: String = "",
    val token: String? = null
)

data class ApiErrorResponse(
    val message: String? = null,
    val error: String? = null
)