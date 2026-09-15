package com.example.freeapp.domain.repository

import com.example.freeapp.domain.auth.RegistrationUser

interface AuthRepository {
    suspend fun cadastrar(usuario: RegistrationUser): Result<Unit>

    suspend fun login(email: String, senha: String): Result<Unit>

    suspend fun buscarNomeUsuarioAtual(): Result<String>

    suspend fun redefinirSenha(email: String): Result<Unit>

    fun sair()
}
