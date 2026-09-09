package com.example.freeapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freeapp.data.remote.ClienteIbge
import com.example.freeapp.data.remote.dto.EstadoIbge
import com.example.freeapp.data.repository.RepositorioFirebase
import com.example.freeapp.data.repository.RepositorioIbge
import com.example.freeapp.domain.User
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val authenticationRepository = RepositorioFirebase()
    private val ibgeRepository = RepositorioIbge(ClienteIbge.servicoIbge)

    var user by mutableStateOf(User())
        private set

    var authLoading by mutableStateOf(false)
        private set

    var authErrorMessage by mutableStateOf<String?>(null)
        private set

    var ibgeStates by mutableStateOf<List<EstadoIbge>>(emptyList())
        private set

    var ibgeCities by mutableStateOf<List<String>>(emptyList())
        private set

    var ibgeLoading by mutableStateOf(false)
        private set

    var ibgeErrorMessage by mutableStateOf<String?>(null)
        private set

    fun clearAuthState() {
        authErrorMessage = null
    }

    fun updateName(value: String) { user = user.copy(personalData = user.personalData.copy(name = value)) }
    fun updateBirthDate(value: String) { user = user.copy(personalData = user.personalData.copy(birthDate = value)) }
    fun updateCpf(value: String) { user = user.copy(personalData = user.personalData.copy(cpf = value)) }
    fun updateEmail(value: String) { user = user.copy(personalData = user.personalData.copy(email = value)) }
    fun updateConfirmEmail(value: String) { user = user.copy(personalData = user.personalData.copy(confirmEmail = value)) }
    fun updatePhone(value: String) { user = user.copy(personalData = user.personalData.copy(phone = value)) }
    fun updatePassword(value: String) { user = user.copy(personalData = user.personalData.copy(password = value)) }

    fun updateZipCode(value: String) { user = user.copy(address = user.address.copy(zipCode = value)) }
    fun updateAddress(value: String) { user = user.copy(address = user.address.copy(street = value)) }
    fun updateNumber(value: String) { user = user.copy(address = user.address.copy(number = value)) }
    fun updateComplement(value: String) { user = user.copy(address = user.address.copy(complement = value)) }
    fun updateNeighborhood(value: String) { user = user.copy(address = user.address.copy(neighborhood = value)) }
    fun updateCity(value: String) { user = user.copy(address = user.address.copy(city = value)) }
    fun updateState(value: String) { user = user.copy(address = user.address.copy(state = value)) }

    fun loadIbgeStates() {
        if (ibgeStates.isNotEmpty() || ibgeLoading) return

        viewModelScope.launch {
            ibgeLoading = true
            ibgeErrorMessage = null
            val result = ibgeRepository.buscarEstados()
            ibgeLoading = false

            if (result.isSuccess) {
                ibgeStates = result.getOrDefault(emptyList())
            } else {
                ibgeErrorMessage = result.exceptionOrNull()?.message
            }
        }
    }

    fun selectIbgeState(stateName: String) {
        val selectedState = ibgeStates.firstOrNull { it.nome == stateName }
        updateState(stateName)
        updateCity("")
        ibgeCities = emptyList()

        if (selectedState != null) {
            loadIbgeCities(selectedState.sigla)
        }
    }

    private fun loadIbgeCities(stateCode: String) {
        if (ibgeLoading) return

        viewModelScope.launch {
            ibgeLoading = true
            ibgeErrorMessage = null
            val result = ibgeRepository.buscarCidades(stateCode)
            ibgeLoading = false

            if (result.isSuccess) {
                ibgeCities = result.getOrDefault(emptyList())
            } else {
                ibgeErrorMessage = result.exceptionOrNull()?.message
            }
        }
    }

    fun updateProfession(value: String) { user = user.copy(professionalData = user.professionalData.copy(profession = value)) }
    fun updateSpecialty(value: String) { user = user.copy(professionalData = user.professionalData.copy(specialty = value)) }
    fun updateRegion(value: String) { user = user.copy(professionalData = user.professionalData.copy(region = value)) }
    fun updateSchedule(value: String) { user = user.copy(professionalData = user.professionalData.copy(schedule = value)) }

    fun updateAgency(value: String) { user = user.copy(bankData = user.bankData.copy(agency = value)) }
    fun updateAccount(value: String) { user = user.copy(bankData = user.bankData.copy(account = value)) }
    fun updateAccountType(value: String) { user = user.copy(bankData = user.bankData.copy(accountType = value)) }
    fun updatePix(value: String) { user = user.copy(bankData = user.bankData.copy(pix = value)) }

    fun updatePaymentOption(value: String) { user = user.copy(bankData = user.bankData.copy(paymentOption = value)) }
    fun updateCardNumber(value: String) { user = user.copy(bankData = user.bankData.copy(cardNumber = value)) }
    fun updateCardExpiration(value: String) { user = user.copy(bankData = user.bankData.copy(cardExpiration = value)) }
    fun updateCvv(value: String) { user = user.copy(bankData = user.bankData.copy(cvv = value)) }

    fun registerUser(onSuccess: () -> Unit) {
        if (authLoading) return

        viewModelScope.launch {
            authLoading = true
            authErrorMessage = null
            val result = authenticationRepository.cadastrar(user)
            authLoading = false

            if (result.isSuccess) {
                onSuccess()
            } else {
                authErrorMessage = result.exceptionOrNull()?.message
                    ?: "Não foi possível concluir o cadastro"
            }
        }
    }
}
