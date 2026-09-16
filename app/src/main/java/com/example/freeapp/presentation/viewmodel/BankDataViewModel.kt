package com.example.freeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel

class BankDataViewModel(
    private val registrationViewModel: RegistrationViewModel
) : ViewModel() {
    val uiState = registrationViewModel.uiState

    fun updateAgency(value: String) = registrationViewModel.updateAgency(value)
    fun updateAccount(value: String) = registrationViewModel.updateAccount(value)
    fun updateAccountType(value: String) = registrationViewModel.updateAccountType(value)
    fun updatePix(value: String) = registrationViewModel.updatePix(value)
}
