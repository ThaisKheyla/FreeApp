package com.projeto.ui.validation

import android.util.Patterns

object UsuarioValidator {
    fun nomeValido(nome: String): Boolean {
        return nome.trim().isNotEmpty() &&
                nome.trim().contains(" ")
    }

    fun dataValida(data: String): Boolean {
        return data.matches(
            Regex("""\d{2}/\d{2}/\d{4}""")
        )
    }

    fun cpfValido(cpf: String): Boolean {
        return cpf.all { it.isDigit() } &&
                cpf.length == 11
    }

    fun emailValido(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS
            .matcher(email)
            .matches()
    }

    fun emailsIguais(
        email: String,
        confirmarEmail: String
    ): Boolean {
        return email.trim()
            .equals(
                confirmarEmail.trim(),
                ignoreCase = true
            )
    }

    fun telefoneValido(telefone: String): Boolean {
        return telefone.all { it.isDigit() } &&
                telefone.length >= 10
    }
}