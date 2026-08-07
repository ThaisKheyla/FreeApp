package com.projeto.domain.model

data class Usuario(
    // Dados pessoais
    val nome: String = "",
    val dataNascimento: String = "",
    val cpf: String = "",
    val email: String = "",
    val confirmarEmail: String = "",
    val telefone: String = "",

    // Endereço
    val cep: String = "",
    val endereco: String = "",
    val numero: String = "",
    val complemento: String = "",
    val bairro: String = "",
    val cidade: String = "",
    val estado: String = ""
)