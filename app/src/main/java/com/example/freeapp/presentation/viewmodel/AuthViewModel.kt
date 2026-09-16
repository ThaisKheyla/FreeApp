package com.example.freeapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeapp.domain.auth.AuthUser
import com.example.freeapp.domain.usecase.auth.GetCurrentUserNameUseCase
import com.example.freeapp.domain.usecase.auth.LoginUseCase
import com.example.freeapp.domain.usecase.auth.ResetPasswordUseCase
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val getCurrentUserNameUseCase: GetCurrentUserNameUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    var authenticatedUser by mutableStateOf(
        AuthUser()
    )
        private set

    var authLoading by mutableStateOf(false)
        private set

    var authErrorMessage by mutableStateOf<String?>(null)
        private set

    fun clearAuthState() {
        authErrorMessage = null
    }

    fun loginUser(
        email: String,
        senha: String,
        onSuccess: () -> Unit
    ) {
        if (authLoading) return

        viewModelScope.launch {
            authLoading = true
            authErrorMessage = null

            val resultadoRemoto = loginUseCase(email, senha)

            authLoading = false

            if (resultadoRemoto.isSuccess) {
                val name = getCurrentUserNameUseCase().getOrNull().orEmpty()
                authenticatedUser = authenticatedUser.copy(
                    name = name,
                    email = email
                )
                onSuccess()
            } else {
                authErrorMessage = resultadoRemoto.exceptionOrNull()?.message
                    ?: "E-mail ou senha inválidos"
            }
        }
    }

    fun resetPassword(
        email: String,
        onSuccess: () -> Unit
    ) {
        if (authLoading) return

        viewModelScope.launch {
            authLoading = true
            authErrorMessage = null

            val resultado = resetPasswordUseCase(email)

            authLoading = false

            if (resultado.isSuccess) {
                onSuccess()
            } else {
                authErrorMessage = resultado.exceptionOrNull()?.message
                    ?: "Não foi possível redefinir a senha"
            }
        }
    }



}