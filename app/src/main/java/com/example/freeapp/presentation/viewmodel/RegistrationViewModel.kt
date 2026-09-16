package com.example.freeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeapp.domain.auth.RegistrationUser
import com.example.freeapp.domain.usecase.auth.RegisterUserUseCase
import com.example.freeapp.domain.usecase.location.LoadCitiesUseCase
import com.example.freeapp.domain.usecase.location.LoadStatesUseCase
import com.example.freeapp.presentation.viewmodel.contract.RegistrationEvent
import com.example.freeapp.presentation.viewmodel.contract.RegistrationUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loadStatesUseCase: LoadStatesUseCase,
    private val loadCitiesUseCase: LoadCitiesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistrationUiState())
    val uiState = _uiState.asStateFlow()

    private val _events = Channel<RegistrationEvent>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()

    fun clearAuthState() {
        _uiState.update { state ->
            state.copy(authErrorMessage = null)
        }
    }

    fun updateName(value: String) = updateUser { user ->
        user.copy(personalData = user.personalData.copy(name = value))
    }

    fun updateBirthDate(value: String) = updateUser { user ->
        user.copy(personalData = user.personalData.copy(birthDate = value))
    }

    fun updateCpf(value: String) = updateUser { user ->
        user.copy(personalData = user.personalData.copy(cpf = value))
    }

    fun updateEmail(value: String) = updateUser { user ->
        user.copy(personalData = user.personalData.copy(email = value))
    }

    fun updateConfirmEmail(value: String) = updateUser { user ->
        user.copy(personalData = user.personalData.copy(confirmEmail = value))
    }

    fun updatePhone(value: String) = updateUser { user ->
        user.copy(personalData = user.personalData.copy(phone = value))
    }

    fun updatePassword(value: String) = updateUser { user ->
        user.copy(personalData = user.personalData.copy(password = value))
    }

    fun updateZipCode(value: String) = updateUser { user ->
        user.copy(address = user.address.copy(zipCode = value))
    }

    fun updateAddress(value: String) = updateUser { user ->
        user.copy(address = user.address.copy(street = value))
    }

    fun updateNumber(value: String) = updateUser { user ->
        user.copy(address = user.address.copy(number = value))
    }

    fun updateComplement(value: String) = updateUser { user ->
        user.copy(address = user.address.copy(complement = value))
    }

    fun updateNeighborhood(value: String) = updateUser { user ->
        user.copy(address = user.address.copy(neighborhood = value))
    }

    fun updateCity(value: String) = updateUser { user ->
        user.copy(address = user.address.copy(city = value))
    }

    fun updateState(value: String) = updateUser { user ->
        user.copy(address = user.address.copy(state = value))
    }

    fun loadIbgeStates() {
        val currentState = _uiState.value
        if (currentState.states.isNotEmpty() || currentState.isIbgeLoading) return

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isIbgeLoading = true, ibgeErrorMessage = null)
            }

            val result = loadStatesUseCase()

            if (result.isSuccess) {
                _uiState.update { state ->
                    state.copy(
                        states = result.getOrDefault(emptyList()),
                        isIbgeLoading = false
                    )
                }
            } else {
                _uiState.update { state ->
                    state.copy(
                        isIbgeLoading = false,
                        ibgeErrorMessage = result.exceptionOrNull()?.message
                    )
                }
            }
        }
    }

    fun selectIbgeState(stateName: String) {
        val currentState = _uiState.value
        val selectedState = currentState.states.firstOrNull { it.name == stateName }
        updateState(stateName)
        updateCity("")
        _uiState.update { state ->
            state.copy(cities = emptyList())
        }

        if (selectedState != null) {
            loadIbgeCities(selectedState.code)
        }
    }

    private fun loadIbgeCities(stateCode: String) {
        if (_uiState.value.isIbgeLoading) return

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isIbgeLoading = true, ibgeErrorMessage = null)
            }

            val result = loadCitiesUseCase(stateCode)

            if (result.isSuccess) {
                _uiState.update { state ->
                    state.copy(
                        cities = result.getOrDefault(emptyList()),
                        isIbgeLoading = false
                    )
                }
            } else {
                _uiState.update { state ->
                    state.copy(
                        isIbgeLoading = false,
                        ibgeErrorMessage = result.exceptionOrNull()?.message
                    )
                }
            }
        }
    }

    fun updateProfession(value: String) = updateUser { user ->
        user.copy(professionalData = user.professionalData.copy(profession = value))
    }

    fun updateSpecialty(value: String) = updateUser { user ->
        user.copy(professionalData = user.professionalData.copy(specialty = value))
    }

    fun updateRegion(value: String) = updateUser { user ->
        user.copy(professionalData = user.professionalData.copy(region = value))
    }

    fun updateSchedule(value: String) = updateUser { user ->
        user.copy(professionalData = user.professionalData.copy(schedule = value))
    }

    fun updateAgency(value: String) = updateUser { user ->
        user.copy(bankData = user.bankData.copy(agency = value))
    }

    fun updateAccount(value: String) = updateUser { user ->
        user.copy(bankData = user.bankData.copy(account = value))
    }

    fun updateAccountType(value: String) = updateUser { user ->
        user.copy(bankData = user.bankData.copy(accountType = value))
    }

    fun updatePix(value: String) = updateUser { user ->
        user.copy(bankData = user.bankData.copy(pix = value))
    }

    fun updatePaymentOption(value: String) = updateUser { user ->
        user.copy(bankData = user.bankData.copy(paymentOption = value))
    }

    fun updateCardNumber(value: String) = updateUser { user ->
        user.copy(bankData = user.bankData.copy(cardNumber = value))
    }

    fun updateCardExpiration(value: String) = updateUser { user ->
        user.copy(bankData = user.bankData.copy(cardExpiration = value))
    }

    fun updateCvv(value: String) = updateUser { user ->
        user.copy(bankData = user.bankData.copy(cvv = value))
    }

    fun registerUser() {
        if (_uiState.value.isAuthLoading) return

        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(isAuthLoading = true, authErrorMessage = null)
            }
            val result = registerUserUseCase(_uiState.value.user)

            if (result.isSuccess) {
                _uiState.update { state ->
                    state.copy(isAuthLoading = false, authErrorMessage = null)
                }
                _events.send(RegistrationEvent.RegistrationSuccess)
            } else {
                _uiState.update { state ->
                    state.copy(
                        isAuthLoading = false,
                        authErrorMessage = result.exceptionOrNull()?.message
                            ?: "Não foi possível concluir o cadastro"
                    )
                }
            }
        }
    }

    private fun updateUser(
        updater: (RegistrationUser) -> RegistrationUser
    ) {
        _uiState.update { state ->
            state.copy(user = updater(state.user))
        }
    }
}
