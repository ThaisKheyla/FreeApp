package com.example.freeapp.data.repository

import com.example.freeapp.data.remote.AuthenticationApiService
import com.example.freeapp.data.remote.dto.ApiErrorResponse
import com.example.freeapp.data.remote.dto.AuthenticationResponse
import com.example.freeapp.data.remote.dto.LoginRequest
import com.example.freeapp.data.remote.dto.RegisterRequest
import com.example.freeapp.data.remote.dto.ResetPasswordRequest
import com.example.freeapp.domain.User
import com.google.gson.Gson
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
        user: User
    ): Result<AuthenticationResponse> {

        return executeRequest {

            apiService.register(
                RegisterRequest(
                    name = user.name,
                    birthDate = user.birthDate,
                    cpf = user.cpf,
                    email = user.email,
                    phone = user.phone,
                    password = user.password,
                    zipCode = user.zipCode,
                    address = user.street,
                    number = user.number,
                    complement = user.complement,
                    district = user.neighborhood,
                    city = user.city,
                    state = user.state,
                    profession = user.profession,
                    specialty = user.specialty,
                    region = user.region,
                    schedule = user.schedule,
                    agency = user.agency,
                    account = user.account,
                    accountType = user.accountType,
                    pix = user.pix,
                    paymentMethod = user.paymentOption,
                    cardNumber = user.cardNumber,
                    cardExpiration = user.cardExpiration,
                    cvv = user.cvv
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

            else -> { error.message ?: "Erro inesperado ao comunicar com o servidor."
            }
        }
    }
}