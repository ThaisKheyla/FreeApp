package com.example.freeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel

class BankDataViewModel(
    private val registrationViewModel: RegistrationViewModel = RegistrationViewModel()
) : ViewModel() {
    val user get() = registrationViewModel.user

    fun updateAgency(value: String) = registrationViewModel.updateAgency(value)
    fun updateAccount(value: String) = registrationViewModel.updateAccount(value)
    fun updateAccountType(value: String) = registrationViewModel.updateAccountType(value)
    fun updatePix(value: String) = registrationViewModel.updatePix(value)
}
