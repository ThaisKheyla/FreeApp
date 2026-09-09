package com.example.freeapp.domain

data class PersonalData(
    val name: String = "",
    val birthDate: String = "",
    val cpf: String = "",
    val email: String = "",
    val confirmEmail: String = "",
    val phone: String = "",
    val password: String = ""
)
