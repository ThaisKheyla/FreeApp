package com.example.freeapp.data.repository

import com.google.gson.Gson
import com.example.freeapp.data.remote.AuthenticationApiService
import com.example.freeapp.data.remote.dto.ApiErrorResponse
import com.example.freeapp.data.remote.dto.AuthenticationResponse
import com.example.freeapp.data.remote.dto.LoginRequest
import com.example.freeapp.data.remote.dto.RegisterRequest
import com.example.freeapp.data.remote.dto.ResetPasswordRequest
import com.example.freeapp.domain.Usuario
import retrofit2.HttpException
import java.io.IOException

class AuthenticationRepository(
    private val apiService: AuthenticationApiService
) {

    suspend fun login(
        email: String,
        password: String
    ): Result<AuthenticationResponse> {

        return executeRequest {

            apiService.login(
                LoginRequest(
                    email = email,
                    password = password
                )
            )
        }
    }

    suspend fun register(
        usuario: Usuario
    ): Result<AuthenticationResponse> {

        return executeRequest {

            apiService.register(
                RegisterRequest(
                    name = usuario.nome,
                    birthDate = usuario.dataNascimento,
                    cpf = usuario.cpf,
                    email = usuario.email,
                    phone = usuario.telefone,
                    password = usuario.senha,
                    zipCode = usuario.cep,
                    address = usuario.endereco,
                    number = usuario.numero,
                    complement = usuario.complemento,
                    district = usuario.bairro,
                    city = usuario.cidade,
                    state = usuario.estado,
                    profession = usuario.profissao,
                    specialty = usuario.especialidade,
                    region = usuario.regiao,
                    schedule = usuario.horario,
                    agency = usuario.agencia,
                    account = usuario.conta,
                    accountType = usuario.tipoConta,
                    pix = usuario.pix,
                    paymentMethod = usuario.opcaoPagamento,
                    cardNumber = usuario.numeroCartao,
                    cardExpiration = usuario.validadeCartao,
                    cvv = usuario.cvv
                )
            )
        }
    }

    suspend fun resetPassword(
        email: String,
        newPassword: String
    ): Result<AuthenticationResponse> {

        return executeRequest {

            apiService.resetPassword(
                ResetPasswordRequest(
                    email = email,
                    newPassword = newPassword
                )
            )
        }
    }

    private suspend fun executeRequest(
        block: suspend () -> AuthenticationResponse
    ): Result<AuthenticationResponse> {

        return try {

            Result.success(
                block()
            )

        } catch (error: Throwable) {

            Result.failure(
                Exception(
                    mapErrorMessage(error)
                )
            )
        }
    }

    private fun mapErrorMessage(
        error: Throwable
    ): String {

        return when (error) {

            is HttpException -> {

                val body = error.response()
                    ?.errorBody()
                    ?.string()
                    .orEmpty()

                val response = runCatching {

                    Gson().fromJson(
                        body,
                        ApiErrorResponse::class.java
                    )

                }.getOrNull()

                response?.message
                    ?: response?.error
                    ?: "Erro de servidor (${error.code()})."
            }

            is IOException -> {
                "Sem conexão com a internet."
            }

            else -> {
                error.message
                    ?: "Erro inesperado ao comunicar com o servidor."
            }
        }
    }
}