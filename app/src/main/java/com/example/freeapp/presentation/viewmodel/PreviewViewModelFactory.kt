package com.example.freeapp.presentation.viewmodel

import com.example.freeapp.domain.StateOptionData
import com.example.freeapp.domain.auth.RegistrationUser
import com.example.freeapp.domain.repository.AuthRepository
import com.example.freeapp.domain.repository.LocationRepository
import com.example.freeapp.domain.usecase.auth.GetCurrentUserNameUseCase
import com.example.freeapp.domain.usecase.auth.LoginUseCase
import com.example.freeapp.domain.usecase.auth.RegisterUserUseCase
import com.example.freeapp.domain.usecase.auth.ResetPasswordUseCase
import com.example.freeapp.domain.usecase.location.LoadCitiesUseCase
import com.example.freeapp.domain.usecase.location.LoadStatesUseCase

private class PreviewAuthRepository : AuthRepository {
    override suspend fun cadastrar(usuario: RegistrationUser): Result<Unit> = Result.success(Unit)
    override suspend fun login(email: String, senha: String): Result<Unit> = Result.success(Unit)
    override suspend fun buscarNomeUsuarioAtual(): Result<String> = Result.success("Preview User")
    override suspend fun redefinirSenha(email: String): Result<Unit> = Result.success(Unit)
    override fun sair() = Unit
}


private class PreviewLocationRepository : LocationRepository {
    override suspend fun buscarEstados(): Result<List<StateOptionData>> = Result.success(emptyList())

    override suspend fun buscarCidades(uf: String): Result<List<String>> = Result.success(emptyList())
}

private val previewAuthRepository = PreviewAuthRepository()
private val previewLocationRepository = PreviewLocationRepository()

fun previewAuthViewModel(): AuthViewModel = AuthViewModel(
    loginUseCase = LoginUseCase(previewAuthRepository),
    getCurrentUserNameUseCase = GetCurrentUserNameUseCase(previewAuthRepository),
    resetPasswordUseCase = ResetPasswordUseCase(previewAuthRepository)
)

fun previewRegistrationViewModel(): RegistrationViewModel = RegistrationViewModel(
    registerUserUseCase = RegisterUserUseCase(previewAuthRepository),
    loadStatesUseCase = LoadStatesUseCase(previewLocationRepository),
    loadCitiesUseCase = LoadCitiesUseCase(previewLocationRepository)
)
