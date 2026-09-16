package com.example.freeapp.domain.usecase.auth

import com.example.freeapp.domain.auth.RegistrationUser
import com.example.freeapp.domain.repository.AuthRepository

class RegisterUserUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        user: RegistrationUser
    ): Result<Unit> {
        return authRepository.cadastrar(user)
    }
}
