package com.example.freeapp.data.remote.dto

data class RequisicaoLogin(
    val email: String,
    val senha: String
)

data class RequisicaoCadastro(
    val nome: String,
    val dataNascimento: String,
    val cpf: String,
    val email: String,
    val telefone: String,
    val senha: String,
    val cep: String,
    val endereco: String,
    val numero: String,
    val complemento: String,
    val bairro: String,
    val cidade: String,
    val estado: String,
    val profissao: String,
    val especialidade: String,
    val regiao: String,
    val horario: String,
    val agencia: String,
    val conta: String,
    val tipoConta: String,
    val pix: String,
    val opcaoPagamento: String,
    val numeroCartao: String,
    val validadeCartao: String,
    val cvv: String
)

data class RequisicaoRedefinirSenha(
    val email: String,
    val novaSenha: String
)

data class RespostaAutenticacao(
    val success: Boolean = true,
    val message: String = "",
    val token: String? = null
)

data class RespostaErroApi(
    val message: String? = null,
    val error: String? = null
)