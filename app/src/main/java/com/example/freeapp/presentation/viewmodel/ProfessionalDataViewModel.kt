package com.example.freeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel

class ProfessionalDataViewModel(
    private val registrationViewModel: RegistrationViewModel
) : ViewModel() {
    val uiState = registrationViewModel.uiState

    fun updateProfession(value: String) = registrationViewModel.updateProfession(value)
    fun updateSpecialty(value: String) = registrationViewModel.updateSpecialty(value)
    fun updateRegion(value: String) = registrationViewModel.updateRegion(value)
    fun updateSchedule(value: String) = registrationViewModel.updateSchedule(value)
}
