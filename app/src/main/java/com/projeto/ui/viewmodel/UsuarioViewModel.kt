package com.projeto.ui.viewmodel

import android.R.attr.data
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.projeto.domain.model.Usuario
class UsuarioViewModel : ViewModel() {

    var usuario by mutableStateOf(
        Usuario()
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

    fun atualizarConfirmarEmail(confirmarEmail: String) {
        usuario = usuario.copy(confirmarEmail = confirmarEmail)
    }

    fun atualizarTelefone(telefone: String) {
        usuario = usuario.copy(telefone = telefone)
    }

    // ENDEREÇO

    fun atualizarCep(cep: String) {
        usuario = usuario.copy(cep = cep)
    }

    fun atualizarEndereco(endereco: String) {
        usuario = usuario.copy(endereco = endereco)
    }

    fun atualizarNumero(numero: String) {
        usuario = usuario.copy(numero = numero)
    }

    fun atualizarComplemento(complemento: String) {
        usuario = usuario.copy(complemento = complemento)
    }

    fun atualizarBairro(bairro: String) {
        usuario = usuario.copy(bairro = bairro)
    }

    fun atualizarCidade(cidade: String) {
        usuario = usuario.copy(cidade = cidade)
    }

    fun atualizarEstado(estado: String) {
        usuario = usuario.copy(estado = estado)
    }
}