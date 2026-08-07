package com.projeto.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.projeto.domain.model.Usuario

class UsuarioViewModel : ViewModel() {
    var usuario by mutableStateOf(
        Usuario(
            nome = "",
            dataNascimento = "",
            cpf = "",
            email = "",
            confirmarEmail = "",
            telefone = ""
        )
    )
        private set

    fun atualizarNome(nome: String) {
        usuario = usuario.copy(nome = nome)
    }

    fun atualizarDataNascimento(dataNascimento: String) {
        usuario = usuario.copy(dataNascimento = dataNascimento)
    }

    fun atualizarCpf(cpf: String) {
        usuario = usuario.copy(cpf = cpf)
    }

    fun atualizarEmail(email: String) {
        usuario = usuario.copy(email = email)
    }
    var confirmarEmail by mutableStateOf("")
        private set

    fun atualizarConfirmarEmail(confirmarEmail: String) {
        usuario = usuario.copy(
            confirmarEmail = confirmarEmail
        )
    }

    fun atualizarTelefone(telefone: String) {
        usuario = usuario.copy(telefone = telefone)
    }
}