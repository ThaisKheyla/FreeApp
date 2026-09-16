package com.example.freeapp.presentation.viewmodel.contract

import com.example.freeapp.domain.StateOptionData
import com.example.freeapp.domain.auth.RegistrationUser

data class RegistrationUiState(
    val user: RegistrationUser = RegistrationUser(),
    val isAuthLoading: Boolean = false,
    val authErrorMessage: String? = null,
    val states: List<StateOptionData> = emptyList(),
    val cities: List<String> = emptyList(),
    val isIbgeLoading: Boolean = false,
    val ibgeErrorMessage: String? = null
)

sealed interface RegistrationEvent {
    data object RegistrationSuccess : RegistrationEvent
}
