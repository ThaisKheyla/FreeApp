package com.example.freeapp.data.repository

import com.example.freeapp.data.remote.ServicoIbgeApi
import com.example.freeapp.data.remote.dto.EstadoIbge
import java.io.IOException

class RepositorioIbge(
    private val servicoIbgeApi: ServicoIbgeApi
) {
    suspend fun buscarEstados(): Result<List<EstadoIbge>> {
        return executarRequisicao {
            servicoIbgeApi.buscarEstados()
        }
    }

    suspend fun buscarCidades(uf: String): Result<List<String>> {
        return executarRequisicao {
            servicoIbgeApi.buscarMunicipiosPorEstado(uf).map { municipio -> municipio.nome }
        }
    }

    private suspend fun <T> executarRequisicao(bloco: suspend () -> T): Result<T> {
        return try {
            Result.success(bloco())
        } catch (erro: Throwable) {
            val mensagem = if (erro is IOException) {
                "Sem conexão para carregar dados do IBGE."
            } else {
                erro.message ?: "Não foi possível carregar dados do IBGE."
            }

            Result.failure(Exception(mensagem))
        }
    }
}