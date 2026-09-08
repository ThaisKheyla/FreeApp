package com.example.freeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel

class ProfessionalDataViewModel(
    private val registrationViewModel: RegistrationViewModel = RegistrationViewModel()
) : ViewModel() {
    val user get() = registrationViewModel.user

    fun updateProfession(value: String) = registrationViewModel.updateProfession(value)
    fun updateSpecialty(value: String) = registrationViewModel.updateSpecialty(value)
    fun updateRegion(value: String) = registrationViewModel.updateRegion(value)
    fun updateSchedule(value: String) = registrationViewModel.updateSchedule(value)
}
