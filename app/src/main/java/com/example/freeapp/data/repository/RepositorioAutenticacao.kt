package com.example.freeapp.data.repository

import com.google.gson.Gson
import com.example.freeapp.data.remote.ServicoAutenticacaoApi
import com.example.freeapp.data.remote.dto.RequisicaoCadastro
import com.example.freeapp.data.remote.dto.RequisicaoLogin
import com.example.freeapp.data.remote.dto.RequisicaoRedefinirSenha
import com.example.freeapp.data.remote.dto.RespostaAutenticacao
import com.example.freeapp.data.remote.dto.RespostaErroApi
import com.example.freeapp.domain.User
import retrofit2.HttpException
import java.io.IOException

class RepositorioAutenticacao(
    private val servicoApi: ServicoAutenticacaoApi
) {
    suspend fun login(email: String, senha: String): Result<RespostaAutenticacao> {
        return executarRequisicao {
            servicoApi.login(
                RequisicaoLogin(
                    email = email,
                    senha = senha
                )
            )
        }
    }

    suspend fun cadastrar(usuario: User): Result<RespostaAutenticacao> {
        return executarRequisicao {
            servicoApi.cadastrar(
                RequisicaoCadastro(
                    nome = usuario.name,
                    dataNascimento = usuario.birthDate,
                    cpf = usuario.cpf,
                    email = usuario.email,
                    telefone = usuario.phone,
                    senha = usuario.password,
                    cep = usuario.zipCode,
                    endereco = usuario.street,
                    numero = usuario.number,
                    complemento = usuario.complement,
                    bairro = usuario.neighborhood,
                    cidade = usuario.city,
                    estado = usuario.state,
                    profissao = usuario.profession,
                    especialidade = usuario.specialty,
                    regiao = usuario.region,
                    horario = usuario.schedule,
                    agencia = usuario.agency,
                    conta = usuario.account,
                    tipoConta = usuario.accountType,
                    pix = usuario.pix,
                    opcaoPagamento = usuario.paymentOption,
                    numeroCartao = usuario.cardNumber,
                    validadeCartao = usuario.cardExpiration,
                    cvv = usuario.cvv
                )
            )
        }
    }

    suspend fun redefinirSenha(email: String, novaSenha: String): Result<RespostaAutenticacao> {
        return executarRequisicao {
            servicoApi.redefinirSenha(
                RequisicaoRedefinirSenha(
                    email = email,
                    novaSenha = novaSenha
                )
            )
        }
    }

    private suspend fun executarRequisicao(
        bloco: suspend () -> RespostaAutenticacao
    ): Result<RespostaAutenticacao> {
        return try {
            Result.success(bloco())
        } catch (erro: Throwable) {
            Result.failure(Exception(mapearMensagem(erro)))
        }
    }

    private fun mapearMensagem(erro: Throwable): String {
        return when (erro) {
            is HttpException -> {
                val corpo = erro.response()?.errorBody()?.string().orEmpty()
                val resposta = runCatching {
                    Gson().fromJson(corpo, RespostaErroApi::class.java)
                }.getOrNull()

                resposta?.message
                    ?: resposta?.error
                    ?: "Erro de servidor (${erro.code()})."
            }
            is IOException -> "Sem conexão com a internet."
            else -> erro.message ?: "Erro inesperado ao comunicar com o servidor."
        }
    }
}