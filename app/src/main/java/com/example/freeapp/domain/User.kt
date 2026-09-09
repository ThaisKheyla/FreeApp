package com.example.freeapp.domain

data class User(
    val personalData: PersonalData = PersonalData(),
    val address: Address = Address(),
    val professionalData: ProfessionalData = ProfessionalData(),
    val bankData: BankData = BankData()
) {
    val nome get() = personalData.name
    val dataNascimento get() = personalData.birthDate
    val cpf get() = personalData.cpf
    val email get() = personalData.email
    val confirmarEmail get() = personalData.confirmEmail
    val telefone get() = personalData.phone
    val senha get() = personalData.password

    val cep get() = address.zipCode
    val endereco get() = address.street
    val numero get() = address.number
    val complemento get() = address.complement
    val bairro get() = address.neighborhood
    val cidade get() = address.city
    val estado get() = address.state

    val profissao get() = professionalData.profession
    val especialidade get() = professionalData.specialty
    val regiao get() = professionalData.region
    val horario get() = professionalData.schedule

    val agencia get() = bankData.agency
    val conta get() = bankData.account
    val tipoConta get() = bankData.accountType
    val pix get() = bankData.pix
    val opcaoPagamento get() = bankData.paymentOption
    val numeroCartao get() = bankData.cardNumber
    val validadeCartao get() = bankData.cardExpiration
    val cvv get() = bankData.cvv
}
