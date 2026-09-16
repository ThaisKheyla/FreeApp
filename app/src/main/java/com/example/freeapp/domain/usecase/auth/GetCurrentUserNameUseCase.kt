package com.example.freeapp.domain.usecase.auth

import com.example.freeapp.domain.repository.AuthRepository

class GetCurrentUserNameUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<String> {
        return authRepository.buscarNomeUsuarioAtual()
    }
}
