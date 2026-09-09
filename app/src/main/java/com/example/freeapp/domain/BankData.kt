package com.example.freeapp.domain

data class BankData(
    val agency: String = "",
    val account: String = "",
    val accountType: String = "Pessoa Física",
    val pix: String = "",
    val paymentOption: String = "",
    val cardNumber: String = "",
    val cardExpiration: String = "",
    val cvv: String = ""
)