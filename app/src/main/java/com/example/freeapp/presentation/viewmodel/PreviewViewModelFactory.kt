package com.example.freeapp.presentation.viewmodel

import com.example.freeapp.domain.StateOptionData
import com.example.freeapp.domain.auth.RegistrationUser
import com.example.freeapp.domain.repository.AuthRepository
import com.example.freeapp.domain.repository.LocationRepository

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

fun previewAuthViewModel(): AuthViewModel = AuthViewModel(previewAuthRepository)

fun previewRegistrationViewModel(): RegistrationViewModel = RegistrationViewModel(
    authRepository = previewAuthRepository,
    locationRepository = previewLocationRepository
)
