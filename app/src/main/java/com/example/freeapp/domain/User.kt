package com.example.freeapp.domain

data class User(
    val personalData: PersonalData = PersonalData(),
    val address: Address = Address(),
    val professionalData: ProfessionalData = ProfessionalData(),
    val bankData: BankData = BankData()
) {
    val name get() = personalData.name
    val birthDate get() = personalData.birthDate
    val cpf get() = personalData.cpf
    val email get() = personalData.email
    val confirmEmail get() = personalData.confirmEmail
    val phone get() = personalData.phone
    val password get() = personalData.password

    val zipCode get() = address.zipCode
    val street get() = address.street
    val number get() = address.number
    val complement get() = address.complement
    val neighborhood get() = address.neighborhood
    val city get() = address.city
    val state get() = address.state

    val profession get() = professionalData.profession
    val specialty get() = professionalData.specialty
    val region get() = professionalData.region
    val schedule get() = professionalData.schedule

    val agency get() = bankData.agency
    val account get() = bankData.account
    val accountType get() = bankData.accountType
    val pix get() = bankData.pix
    val paymentOption get() = bankData.paymentOption
    val cardNumber get() = bankData.cardNumber
    val cardExpiration get() = bankData.cardExpiration
    val cvv get() = bankData.cvv
}
