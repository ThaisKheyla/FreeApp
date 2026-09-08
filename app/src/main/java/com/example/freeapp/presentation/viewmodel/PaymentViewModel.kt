package com.example.freeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel

class PaymentViewModel(
    private val registrationViewModel: RegistrationViewModel = RegistrationViewModel()
) : ViewModel() {
    val user get() = registrationViewModel.user

    fun updatePaymentOption(value: String) = registrationViewModel.updatePaymentOption(value)
    fun updateName(value: String) = registrationViewModel.updateName(value)
    fun updateCardNumber(value: String) = registrationViewModel.updateCardNumber(value)
    fun updateCardExpiration(value: String) = registrationViewModel.updateCardExpiration(value)
    fun updateCvv(value: String) = registrationViewModel.updateCvv(value)
}
