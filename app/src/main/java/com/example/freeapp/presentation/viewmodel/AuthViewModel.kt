package com.example.freeapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeapp.data.repository.RepositorioFirebase
import com.example.freeapp.domain.User
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val repositorioAutenticacao = RepositorioFirebase()

    var user by mutableStateOf(
        User()
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

            val resultadoRemoto = repositorioAutenticacao.login(email, senha)

            authLoading = false

            if (resultadoRemoto.isSuccess) {
                val nome = repositorioAutenticacao.buscarNomeUsuarioAtual().getOrNull().orEmpty()
                user = user.copy(
                    personalData = user.personalData.copy(
                        name = nome,
                        email = email
                    )
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

            val resultado = repositorioAutenticacao.redefinirSenha(email)

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