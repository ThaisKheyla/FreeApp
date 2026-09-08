package com.example.freeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel

class PersonalDataViewModel(
    private val registrationViewModel: RegistrationViewModel = RegistrationViewModel()
) : ViewModel() {
    val user get() = registrationViewModel.user

    fun updateName(value: String) = registrationViewModel.updateName(value)
    fun updateBirthDate(value: String) = registrationViewModel.updateBirthDate(value)
    fun updateCpf(value: String) = registrationViewModel.updateCpf(value)
    fun updateEmail(value: String) = registrationViewModel.updateEmail(value)
    fun updateConfirmEmail(value: String) = registrationViewModel.updateConfirmEmail(value)
    fun updatePhone(value: String) = registrationViewModel.updatePhone(value)
}
