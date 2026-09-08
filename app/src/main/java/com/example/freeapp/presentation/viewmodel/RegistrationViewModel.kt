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
import com.example.freeapp.domain.Usuario
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    private val authenticationRepository = RepositorioFirebase()
    private val ibgeRepository = RepositorioIbge(ClienteIbge.servicoIbge)

    var user by mutableStateOf(Usuario())
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

    fun updateName(value: String) { user = user.copy(nome = value) }
    fun updateBirthDate(value: String) { user = user.copy(dataNascimento = value) }
    fun updateCpf(value: String) { user = user.copy(cpf = value) }
    fun updateEmail(value: String) { user = user.copy(email = value) }
    fun updateConfirmEmail(value: String) { user = user.copy(confirmarEmail = value) }
    fun updatePhone(value: String) { user = user.copy(telefone = value) }
    fun updatePassword(value: String) { user = user.copy(senha = value) }

    fun updateZipCode(value: String) { user = user.copy(cep = value) }
    fun updateAddress(value: String) { user = user.copy(endereco = value) }
    fun updateNumber(value: String) { user = user.copy(numero = value) }
    fun updateComplement(value: String) { user = user.copy(complemento = value) }
    fun updateNeighborhood(value: String) { user = user.copy(bairro = value) }
    fun updateCity(value: String) { user = user.copy(cidade = value) }
    fun updateState(value: String) { user = user.copy(estado = value) }

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

    fun updateProfession(value: String) { user = user.copy(profissao = value) }
    fun updateSpecialty(value: String) { user = user.copy(especialidade = value) }
    fun updateRegion(value: String) { user = user.copy(regiao = value) }
    fun updateSchedule(value: String) { user = user.copy(horario = value) }

    fun updateAgency(value: String) { user = user.copy(agencia = value) }
    fun updateAccount(value: String) { user = user.copy(conta = value) }
    fun updateAccountType(value: String) { user = user.copy(tipoConta = value) }
    fun updatePix(value: String) { user = user.copy(pix = value) }

    fun updatePaymentOption(value: String) { user = user.copy(opcaoPagamento = value) }
    fun updateCardNumber(value: String) { user = user.copy(numeroCartao = value) }
    fun updateCardExpiration(value: String) { user = user.copy(validadeCartao = value) }
    fun updateCvv(value: String) { user = user.copy(cvv = value) }

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
