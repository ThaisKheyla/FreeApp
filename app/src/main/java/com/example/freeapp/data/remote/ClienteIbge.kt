package com.example.freeapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClienteIbge {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://servicodados.ibge.gov.br/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val servicoIbge: ServicoIbgeApi by lazy {
        retrofit.create(ServicoIbgeApi::class.java)
    }
}