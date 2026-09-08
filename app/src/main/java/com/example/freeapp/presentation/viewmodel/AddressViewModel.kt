package com.example.freeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel

class AddressViewModel(
    private val registrationViewModel: RegistrationViewModel = RegistrationViewModel()
) : ViewModel() {
    val user get() = registrationViewModel.user
    val ibgeStates get() = registrationViewModel.ibgeStates
    val ibgeCities get() = registrationViewModel.ibgeCities
    val ibgeLoading get() = registrationViewModel.ibgeLoading
    val ibgeErrorMessage get() = registrationViewModel.ibgeErrorMessage

    fun updateZipCode(value: String) = registrationViewModel.updateZipCode(value)
    fun updateAddress(value: String) = registrationViewModel.updateAddress(value)
    fun updateNumber(value: String) = registrationViewModel.updateNumber(value)
    fun updateComplement(value: String) = registrationViewModel.updateComplement(value)
    fun updateNeighborhood(value: String) = registrationViewModel.updateNeighborhood(value)
    fun updateCity(value: String) = registrationViewModel.updateCity(value)
    fun loadIbgeStates() = registrationViewModel.loadIbgeStates()
    fun selectIbgeState(value: String) = registrationViewModel.selectIbgeState(value)
}
