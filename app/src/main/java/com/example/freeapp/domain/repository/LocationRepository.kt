package com.example.freeapp.domain.repository

import com.example.freeapp.domain.StateOptionData

interface LocationRepository {
    suspend fun buscarEstados(): Result<List<StateOptionData>>

    suspend fun buscarCidades(uf: String): Result<List<String>>
}
