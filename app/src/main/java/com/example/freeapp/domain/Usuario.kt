package com.example.freeapp.domain

data class Usuario(
    // Dados pessoais
    val nome: String = "",
    val dataNascimento: String = "",
    val cpf: String = "",
    val email: String = "",
    val confirmarEmail: String = "",
    val telefone: String = "",
    val senha: String = "",

    // Endereço
    val cep: String = "",
    val endereco: String = "",
    val numero: String = "",
    val complemento: String = "",
    val bairro: String = "",
    val cidade: String = "",
    val estado: String = "",

    //Dados profissionais
    val profissao: String = "",
    val especialidade: String = "",
    val regiao: String = "",
    val horario: String = "",

    //Dados bancarios
    val agencia: String = "",
    val conta: String = "",
    val tipoConta: String = "Pessoa Física",
    val pix: String = "",
    val opcaoPagamento: String = "",
    val numeroCartao: String = "",
    val validadeCartao: String = "",
    val cvv: String = ""

)