package com.seuprojeto.di

import com.seuprojeto.data.ProjetoDataSource
import com.seuprojeto.repository.ProjetoRepository

object GuiaModule {
    val projetoRepository: ProjetoRepository by lazy {
        ProjetoRepository(ProjetoDataSource())
    }
}