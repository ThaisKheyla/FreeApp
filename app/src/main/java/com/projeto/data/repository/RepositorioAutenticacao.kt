package com.projeto.data.repository

import com.google.gson.Gson
import com.projeto.data.remote.ServicoAutenticacaoApi
import com.projeto.data.remote.dto.RequisicaoCadastro
import com.projeto.data.remote.dto.RequisicaoLogin
import com.projeto.data.remote.dto.RequisicaoRedefinirSenha
import com.projeto.data.remote.dto.RespostaAutenticacao
import com.projeto.data.remote.dto.RespostaErroApi
import com.projeto.domain.model.Usuario
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

    suspend fun cadastrar(usuario: Usuario): Result<RespostaAutenticacao> {
        return executarRequisicao {
            servicoApi.cadastrar(
                RequisicaoCadastro(
                    nome = usuario.nome,
                    dataNascimento = usuario.dataNascimento,
                    cpf = usuario.cpf,
                    email = usuario.email,
                    telefone = usuario.telefone,
                    senha = usuario.senha,
                    cep = usuario.cep,
                    endereco = usuario.endereco,
                    numero = usuario.numero,
                    complemento = usuario.complemento,
                    bairro = usuario.bairro,
                    cidade = usuario.cidade,
                    estado = usuario.estado,
                    profissao = usuario.profissao,
                    especialidade = usuario.especialidade,
                    regiao = usuario.regiao,
                    horario = usuario.horario,
                    agencia = usuario.agencia,
                    conta = usuario.conta,
                    tipoConta = usuario.tipoConta,
                    pix = usuario.pix,
                    opcaoPagamento = usuario.opcaoPagamento,
                    numeroCartao = usuario.numeroCartao,
                    validadeCartao = usuario.validadeCartao,
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