package com.projeto.domain.model

data class Usuario(
    val nome: String,
    val dataNascimento: String,
    val cpf: String,
    val email: String,
    val confirmarEmail: String,
    val telefone: String
)