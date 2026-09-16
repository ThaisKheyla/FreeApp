package com.example.freeapp.data.repository

import com.example.freeapp.data.remote.ServicoIbgeApi
import com.example.freeapp.domain.StateOptionData
import com.example.freeapp.domain.repository.LocationRepository
import java.io.IOException

class IbgeRepository(
    private val servicoIbgeApi: ServicoIbgeApi
) : LocationRepository {
    override suspend fun buscarEstados(): Result<List<StateOptionData>> {
        return executarRequisicao {
            servicoIbgeApi.buscarEstados().map { estado ->
                StateOptionData(
                    code = estado.sigla,
                    name = estado.nome
                )
            }
        }
    }

    override suspend fun buscarCidades(uf: String): Result<List<String>> {
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