package com.example.freeapp.domain.usecase.auth

import com.example.freeapp.domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<Unit> {
        return authRepository.login(email, password)
    }
}
