package com.seuprojeto.repository

import com.seuprojeto.data.ProjetoDataSource
import com.seuprojeto.domain.model.Projeto

class ProjetoRepository(
    private val dataSource: ProjetoDataSource
) {
    fun buscarProjetoInicial(): Projeto {
        return Projeto(
            nome = dataSource.obterNomeDoProjeto(),
            descricao = "Primeira tela simples para guiar a estrutura do projeto."
        )
    }
}