package com.example.freeapp.data.remote

import com.example.freeapp.data.remote.dto.EstadoIbge
import com.example.freeapp.data.remote.dto.MunicipioIbge
import retrofit2.http.GET
import retrofit2.http.Path

interface ServicoIbgeApi {
    @GET("api/v1/localidades/estados?orderBy=nome")
    suspend fun buscarEstados(): List<EstadoIbge>

    @GET("api/v1/localidades/estados/{uf}/municipios?orderBy=nome")
    suspend fun buscarMunicipiosPorEstado(@Path("uf") uf: String): List<MunicipioIbge>
}