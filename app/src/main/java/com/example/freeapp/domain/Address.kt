package com.example.freeapp.domain

data class Address(
    val zipCode: String = "",
    val street: String = "",
    val number: String = "",
    val complement: String = "",
    val neighborhood: String = "",
    val city: String = "",
    val state: String = ""
)
