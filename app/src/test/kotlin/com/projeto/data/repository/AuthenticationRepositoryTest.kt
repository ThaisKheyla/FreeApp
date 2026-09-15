package com.projeto.data.repository

import com.example.freeapp.data.remote.AuthenticationApiService
import com.example.freeapp.data.remote.dto.ApiErrorResponse
import com.example.freeapp.data.remote.dto.AuthenticationResponse
import com.example.freeapp.data.remote.dto.LoginRequest
import com.example.freeapp.data.remote.dto.RegisterRequest
import com.example.freeapp.data.remote.dto.ResetPasswordRequest
import com.example.freeapp.data.repository.AuthenticationRepository
import com.example.freeapp.domain.PersonalData
import com.example.freeapp.domain.User
import com.google.gson.Gson
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import java.io.IOException

class AuthenticationRepositoryTest {

    private val apiService = mockk<AuthenticationApiService>()
    private val repository = AuthenticationRepository(apiService)

    @Test
    fun login_whenApiReturnsSuccess_shouldReturnSuccess() = runTest {

        val response = mockk<AuthenticationResponse>()

        coEvery {
            apiService.login(any())
        } returns response

        val result = repository.login(
            email = "teste@email.com",
            password = "123456"
        )

        assertTrue(result.isSuccess)
        assertEquals(response, result.getOrNull())
    }

    @Test
    fun login_whenNoInternet_shouldReturnError() = runTest {

        coEvery {
            apiService.login(any())
        } throws IOException()

        val result = repository.login(
            email = "teste@email.com",
            password = "123456"
        )

        assertTrue(result.isFailure)

        assertEquals(
            "Sem conexão com a internet.",
            result.exceptionOrNull()?.message
        )
    }

    @Test
    fun register_whenApiReturnsSuccess_shouldReturnSuccess() = runTest {

        val user = User(
            personalData = PersonalData(
                name = "Marcela",
                email = "teste@email.com",
                password = "123456"
            )
        )

        val response = mockk<AuthenticationResponse>()

        coEvery {
            apiService.register(any())
        } returns response

        val result = repository.register(user)

        assertTrue(result.isSuccess)
        assertEquals(response, result.getOrNull())
    }

    @Test
    fun register_whenNoInternet_shouldReturnError() = runTest {

        val user = User(
            personalData = PersonalData(
                name = "Marcela",
                email = "teste@email.com",
                password = "123456"
            )
        )

        coEvery {
            apiService.register(any())
        } throws IOException()

        val result = repository.register(user)

        assertTrue(result.isFailure)

        assertEquals(
            "Sem conexão com a internet.",
            result.exceptionOrNull()?.message
        )
    }

    @Test
    fun resetPassword_whenApiReturnsSuccess_shouldReturnSuccess() = runTest {

        val response = mockk<AuthenticationResponse>()

        coEvery {
            apiService.resetPassword(any())
        } returns response

        val result = repository.resetPassword(
            email = "teste@email.com",
            newPassword = "123456"
        )

        assertTrue(result.isSuccess)
        assertEquals(response, result.getOrNull())
    }

    @Test
    fun resetPassword_whenNoInternet_shouldReturnError() = runTest {

        coEvery {
            apiService.resetPassword(any())
        } throws IOException()

        val result = repository.resetPassword(
            email = "teste@email.com",
            newPassword = "123456"
        )

        assertTrue(result.isFailure)

        assertEquals(
            "Sem conexão com a internet.",
            result.exceptionOrNull()?.message
        )
    }
}

