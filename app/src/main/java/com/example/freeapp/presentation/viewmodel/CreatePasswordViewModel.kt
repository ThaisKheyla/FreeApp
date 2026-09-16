package com.example.freeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel

class CreatePasswordViewModel(
    private val registrationViewModel: RegistrationViewModel
) : ViewModel() {
    val uiState = registrationViewModel.uiState
    val events = registrationViewModel.events

    fun updatePassword(value: String) = registrationViewModel.updatePassword(value)
    fun clearAuthState() = registrationViewModel.clearAuthState()
    fun registerUser() = registrationViewModel.registerUser()
}
