package com.projeto.data.repository

import com.projeto.data.remote.ServicoAutenticacaoApi
import com.projeto.data.remote.dto.RespostaAutenticacao
import com.projeto.domain.model.Usuario
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class RepositorioAutenticacaoTest {

    private val servicoApi = mockk<ServicoAutenticacaoApi>()
    private val repositoryMock = RepositorioAutenticacao(servicoApi)

    @Test
    fun login_quandoApiRetornaSucesso_deveRetornarSucesso() = runTest {
        val resposta = mockk<RespostaAutenticacao>()

        coEvery { servicoApi.login(any()) } returns resposta

        val resultado = repositoryMock.login("teste@email.com", "123456")

        assertTrue(resultado.isSuccess)
        assertEquals(resposta, resultado.getOrNull())
    }

    @Test
    fun login_quandoSemInternet_deveRetornarErro() = runTest {
        coEvery { servicoApi.login(any()) } throws IOException()

        val resultado = repositoryMock.login("teste@email.com", "123456")

        assertTrue(resultado.isFailure)
        assertEquals("Sem conexão com a internet.", resultado.exceptionOrNull()?.message)
    }

    @Test
    fun cadastrar_quandoApiRetornaSucesso_deveRetornarSucesso() = runTest {
        val usuario = Usuario(nome = "Marcela", email = "teste@email.com", senha = "123456")
        val resposta = mockk<RespostaAutenticacao>()

        coEvery { servicoApi.cadastrar(any()) } returns resposta

        val resultado = repositoryMock.cadastrar(usuario)

        assertTrue(resultado.isSuccess)
        assertEquals(resposta, resultado.getOrNull())
    }

    @Test
    fun cadastrar_quandoSemInternet_deveRetornarErro() = runTest {
        val usuario = Usuario(nome = "Marcela", email = "teste@email.com", senha = "123456")

        coEvery { servicoApi.cadastrar(any()) } throws IOException()

        val resultado = repositoryMock.cadastrar(usuario)

        assertTrue(resultado.isFailure)
        assertEquals("Sem conexão com a internet.", resultado.exceptionOrNull()?.message)
    }

    @Test
    fun redefinirSenha_quandoApiRetornaSucesso_deveRetornarSucesso() = runTest {
        val resposta = mockk<RespostaAutenticacao>()

        coEvery { servicoApi.redefinirSenha(any()) } returns resposta

        val resultado = repositoryMock.redefinirSenha("teste@email.com", "123456")

        assertTrue(resultado.isSuccess)
        assertEquals(resposta, resultado.getOrNull())
    }

    @Test
    fun redefinirSenha_quandoSemInternet_deveRetornarErro() = runTest {
        coEvery { servicoApi.redefinirSenha(any()) } throws IOException()

        val resultado = repositoryMock.redefinirSenha("teste@email.com", "123456")

        assertTrue(resultado.isFailure)
        assertEquals("Sem conexão com a internet.", resultado.exceptionOrNull()?.message)
    }
}