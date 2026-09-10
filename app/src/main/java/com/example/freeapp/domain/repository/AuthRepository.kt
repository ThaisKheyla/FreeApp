package com.example.freeapp.domain.repository

import com.example.freeapp.domain.User

interface AuthRepository {
    suspend fun cadastrar(usuario: User): Result<Unit>

    suspend fun login(email: String, senha: String): Result<Unit>

    suspend fun buscarNomeUsuarioAtual(): Result<String>

    suspend fun redefinirSenha(email: String): Result<Unit>

    fun sair()
}
