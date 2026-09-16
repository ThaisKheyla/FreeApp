package com.example.freeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel

class PersonalDataViewModel(
    private val registrationViewModel: RegistrationViewModel
) : ViewModel() {
    val uiState = registrationViewModel.uiState

    fun updateName(value: String) = registrationViewModel.updateName(value)
    fun updateBirthDate(value: String) = registrationViewModel.updateBirthDate(value)
    fun updateCpf(value: String) = registrationViewModel.updateCpf(value)
    fun updateEmail(value: String) = registrationViewModel.updateEmail(value)
    fun updateConfirmEmail(value: String) = registrationViewModel.updateConfirmEmail(value)
    fun updatePhone(value: String) = registrationViewModel.updatePhone(value)
}
