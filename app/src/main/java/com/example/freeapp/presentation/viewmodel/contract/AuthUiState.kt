package com.example.freeapp.presentation.viewmodel.contract

import com.example.freeapp.domain.auth.AuthUser

data class AuthUiState(
    val authenticatedUser: AuthUser = AuthUser(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed interface AuthEvent {
    data object LoginSuccess : AuthEvent
    data object ResetPasswordSuccess : AuthEvent
}
