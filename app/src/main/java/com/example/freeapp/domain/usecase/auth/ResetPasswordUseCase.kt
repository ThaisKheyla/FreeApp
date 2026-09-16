package com.example.freeapp.domain.usecase.auth

import com.example.freeapp.domain.repository.AuthRepository

class ResetPasswordUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String
    ): Result<Unit> {
        return authRepository.redefinirSenha(email)
    }
}
