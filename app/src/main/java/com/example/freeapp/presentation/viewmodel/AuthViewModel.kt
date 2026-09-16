package com.example.freeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeapp.domain.usecase.auth.GetCurrentUserNameUseCase
import com.example.freeapp.domain.usecase.auth.LoginUseCase
import com.example.freeapp.domain.usecase.auth.ResetPasswordUseCase
import com.example.freeapp.presentation.viewmodel.contract.AuthEvent
import com.example.freeapp.presentation.viewmodel.contract.AuthUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val getCurrentUserNameUseCase: GetCurrentUserNameUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<AuthEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun clearAuthState() {
        _uiState.update { state ->
            state.copy(errorMessage = null)
        }
    }

    fun loginUser(
        email: String,
        senha: String
    ) {
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true, errorMessage = null)
            }

            val resultadoRemoto = loginUseCase(email, senha)

            if (resultadoRemoto.isSuccess) {
                val name = getCurrentUserNameUseCase().getOrNull().orEmpty()
                _uiState.update { state ->
                    state.copy(
                        authenticatedUser = state.authenticatedUser.copy(
                            name = name,
                            email = email
                        ),
                        isLoading = false,
                        errorMessage = null
                    )
                }
                _events.send(AuthEvent.LoginSuccess)
            } else {
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        errorMessage = resultadoRemoto.exceptionOrNull()?.message
                            ?: "E-mail ou senha inválidos"
                    )
                }
            }
        }
    }

    fun resetPassword(
        email: String
    ) {
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isLoading = true, errorMessage = null)
            }

            val resultado = resetPasswordUseCase(email)

            if (resultado.isSuccess) {
                _uiState.update { state ->
                    state.copy(isLoading = false, errorMessage = null)
                }
                _events.send(AuthEvent.ResetPasswordSuccess)
            } else {
                _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        errorMessage = resultado.exceptionOrNull()?.message
                            ?: "Não foi possível redefinir a senha"
                    )
                }
            }
        }
    }
}