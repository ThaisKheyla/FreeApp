package com.example.freeapp.domain.auth

data class AddressData(
    val zipCode: String = "",
    val street: String = "",
    val number: String = "",
    val complement: String = "",
    val neighborhood: String = "",
    val city: String = "",
    val state: String = ""
)
