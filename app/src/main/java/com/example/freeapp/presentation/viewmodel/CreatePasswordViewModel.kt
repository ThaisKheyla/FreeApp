package com.example.freeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel

class CreatePasswordViewModel(
    private val registrationViewModel: RegistrationViewModel = RegistrationViewModel()
) : ViewModel() {
    val user get() = registrationViewModel.user
    val authLoading get() = registrationViewModel.authLoading
    val authErrorMessage get() = registrationViewModel.authErrorMessage

    fun updatePassword(value: String) = registrationViewModel.updatePassword(value)
    fun clearAuthState() = registrationViewModel.clearAuthState()
    fun registerUser(onSuccess: () -> Unit) = registrationViewModel.registerUser(onSuccess)
}
