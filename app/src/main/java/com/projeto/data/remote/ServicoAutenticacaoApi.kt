package com.projeto.data.remote

import com.projeto.data.remote.dto.RequisicaoCadastro
import com.projeto.data.remote.dto.RequisicaoLogin
import com.projeto.data.remote.dto.RequisicaoRedefinirSenha
import com.projeto.data.remote.dto.RespostaAutenticacao
import retrofit2.http.Body
import retrofit2.http.POST

interface ServicoAutenticacaoApi {
    @POST("auth/login")
    suspend fun login(@Body requisicao: RequisicaoLogin): RespostaAutenticacao

    @POST("auth/register")
    suspend fun cadastrar(@Body requisicao: RequisicaoCadastro): RespostaAutenticacao

    @POST("auth/reset-password")
    suspend fun redefinirSenha(@Body requisicao: RequisicaoRedefinirSenha): RespostaAutenticacao
}