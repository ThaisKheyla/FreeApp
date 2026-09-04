package com.projeto.data.repository

import com.example.freeapp.data.remote.ServicoIbgeApi
import com.example.freeapp.data.remote.dto.EstadoIbge
import com.example.freeapp.data.remote.dto.MunicipioIbge
import com.example.freeapp.data.repository.RepositorioIbge
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class RepositorioIbgeTest {
    private val servicoIbgeApi = mockk<ServicoIbgeApi>()
    private val repositoryMock = RepositorioIbge(servicoIbgeApi);


    @Test
    fun buscarEstados_quandoApiRetornaLista_deveRetornarSucesso() = runTest{
        coEvery {
            servicoIbgeApi.buscarEstados()
        } returns emptyList()

        val resultado = repositoryMock.buscarEstados()
        assertTrue(resultado.isSuccess)
    }

    @Test
    fun buscarEstados_quandoApiRetornaEstados_deveRetornarMesmaLista() = runTest {
        val estadosEsperados = listOf(
            EstadoIbge(
                id = 35,
                sigla = "SP",
                nome = "São Paulo"
            ),
            EstadoIbge(
                id = 33,
                sigla = "RJ",
                nome = "Rio de Janeiro"
            )
        )

        coEvery {
            servicoIbgeApi.buscarEstados()
        } returns estadosEsperados

        val resultado = repositoryMock.buscarEstados()

        assertTrue(resultado.isSuccess)
        assertEquals(estadosEsperados, resultado.getOrNull())
    }

    @Test
    fun buscarEstados_quandoSemInternet_deveRetornarErro() = runTest {
        coEvery {
            servicoIbgeApi.buscarEstados()
        } throws IOException()

        val resultado = repositoryMock.buscarEstados()

        assertTrue(resultado.isFailure)
        assertEquals(
            "Sem conexão para carregar dados do IBGE.",
            resultado.exceptionOrNull()?.message
        )
    }

    @Test
    fun buscarCidades_quandoApiRetornaMunicipios_deveRetornarNomesDasCidades() = runTest {
        val municipios = listOf(
            MunicipioIbge(
                id = 1,
                nome = "São Paulo"
            ),
            MunicipioIbge(
                id = 2,
                nome = "Campinas"
            )
        )

        coEvery {
            servicoIbgeApi.buscarMunicipiosPorEstado("SP")
        } returns municipios

        val resultado = repositoryMock.buscarCidades("SP")

        assertTrue(resultado.isSuccess)
        assertEquals(
            listOf("São Paulo", "Campinas"),
            resultado.getOrNull()
        )
    }

    @Test
    fun buscarCidades_quandoSemInternet_deveRetornarErro() = runTest {
        coEvery {
            servicoIbgeApi.buscarMunicipiosPorEstado("SP")
        } throws IOException()

        val resultado = repositoryMock.buscarCidades("SP")

        assertTrue(resultado.isFailure)
        assertEquals(
            "Sem conexão para carregar dados do IBGE.",
            resultado.exceptionOrNull()?.message
        )
    }
}