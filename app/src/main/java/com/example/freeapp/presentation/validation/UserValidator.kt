package com.example.freeapp.presentation.validation

import android.util.Patterns

object UserValidator {

    fun isValidName(
        name: String
    ): Boolean {
        return name.trim().isNotEmpty() &&
                name.trim().contains(" ")
    }

    fun isValidBirthDate(
        birthDate: String
    ): Boolean {
        return birthDate.matches(
            Regex("""\d{2}/\d{2}/\d{4}""")
        )
    }

    fun isValidCpf(
        cpf: String
    ): Boolean {
        return cpf.all { it.isDigit() } &&
                cpf.length == 11
    }

    fun isValidEmail(
        email: String
    ): Boolean {
        return Patterns.EMAIL_ADDRESS
            .matcher(email)
            .matches()
    }

    fun areEmailsEqual(
        email: String,
        confirmEmail: String
    ): Boolean {
        return email.trim().equals(
            confirmEmail.trim(),
            ignoreCase = true
        )
    }

    fun isValidPhone(
        phone: String
    ): Boolean {
        return phone.all { it.isDigit() } &&
                phone.length >= 10
    }
}