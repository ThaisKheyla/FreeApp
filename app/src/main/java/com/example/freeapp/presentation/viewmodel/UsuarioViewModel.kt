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

class UsuarioViewModel : ViewModel() {
    private val repositorioAutenticacao = RepositorioFirebase()
    private val repositorioIbge = RepositorioIbge(ClienteIbge.servicoIbge)

    //dados pessoais
    var usuario by mutableStateOf(
        Usuario()
    )
        private set
        
    val user: Usuario
        get() = usuario

    var authCarregando by mutableStateOf(false)
        private set

    var authErroMensagem by mutableStateOf<String?>(null)
        private set

    var estadosIbge by mutableStateOf<List<EstadoIbge>>(emptyList())
        private set

    var cidadesIbge by mutableStateOf<List<String>>(emptyList())
        private set

    var ibgeCarregando by mutableStateOf(false)
        private set

    var ibgeErroMensagem by mutableStateOf<String?>(null)
        private set

    fun limparEstadoAuth() {
        authErroMensagem = null
    }

    fun clearAuthState() = limparEstadoAuth()

    fun updateName(name: String) {
        usuario = usuario.copy(nome = name)
    }

    fun updateBirthDate(birthDate: String) {
        usuario = usuario.copy(dataNascimento = birthDate)
    }

    fun updateCpf(cpf: String) {
        usuario = usuario.copy(cpf = cpf)
    }

    fun updateEmail(email: String) {
        usuario = usuario.copy(email = email)
    }

    fun updateConfirmEmail(confirmEmail: String) {
        usuario = usuario.copy(confirmarEmail = confirmEmail)
    }

    fun updatePhone(phone: String) {
        usuario = usuario.copy(telefone = phone)
    }

    fun updatePassword(password: String) {
        usuario = usuario.copy(senha = password)
    }

    // ENDEREÇO pessoal

    fun updateZipCode(zipCode: String) {
        usuario = usuario.copy(cep = zipCode)
    }

    fun updateAddress(address: String) {
        usuario = usuario.copy(endereco = address)
    }

    fun updateNumber(number: String) {
        usuario = usuario.copy(numero = number)
    }

    fun updateComplement(complement: String) {
        usuario = usuario.copy(complemento = complement)
    }

    fun updateNeighborhood(neighborhood: String) {
        usuario = usuario.copy(bairro = neighborhood)
    }

    fun updateCity(city: String) {
        usuario = usuario.copy(cidade = city)
    }

    fun updateState(state: String) {
        usuario = usuario.copy(estado = state)
    }

    fun loadIbgeStates() {
        if (estadosIbge.isNotEmpty() || ibgeCarregando) return

        viewModelScope.launch {
            ibgeCarregando = true
            ibgeErroMensagem = null

            val resultado = repositorioIbge.buscarEstados()

            ibgeCarregando = false

            if (resultado.isSuccess) {
                estadosIbge = resultado.getOrDefault(emptyList())
            } else {
                ibgeErroMensagem = resultado.exceptionOrNull()?.message
            }
        }
    }

    fun selectIbgeState(stateName: String) {
        val selectedState = estadosIbge.firstOrNull { state -> state.nome == stateName }

        updateState(stateName)
        updateCity("")
        cidadesIbge = emptyList()

        if (selectedState != null) {
            loadIbgeCities(selectedState.sigla)
        }
    }

    fun loadIbgeCities(stateCode: String) {
        if (ibgeCarregando) return

        viewModelScope.launch {
            ibgeCarregando = true
            ibgeErroMensagem = null

            val resultado = repositorioIbge.buscarCidades(stateCode)

            ibgeCarregando = false

            if (resultado.isSuccess) {
                cidadesIbge = resultado.getOrDefault(emptyList())
            } else {
                ibgeErroMensagem = resultado.exceptionOrNull()?.message
            }
        }
    }


    // DADOS PROFISSIONAIS

    fun updateProfession(profession: String) {
        usuario = usuario.copy(profissao = profession)
    }


    fun updateSpecialty(specialty: String) {
        usuario = usuario.copy(especialidade = specialty)
    }

    fun updateRegion(region: String) {
        usuario = usuario.copy(regiao = region)
    }

    fun updateSchedule(schedule: String) {
        usuario = usuario.copy(horario = schedule)
    }

    //Dados bancarios
    fun updateAgency(agency: String) {
        usuario = usuario.copy(agencia = agency)
    }

    fun updateAccount(account: String) {
        usuario = usuario.copy(conta = account)
    }

    fun updateAccountType(accountType: String) {
        usuario = usuario.copy(tipoConta = accountType)
    }

    fun updatePix(pix: String) {
        usuario = usuario.copy(pix = pix)
    }

    fun updatePaymentOption(paymentOption: String) {
        usuario = usuario.copy(opcaoPagamento = paymentOption)
    }

    //Pagamento
    fun updateCardNumber(cardNumber: String) {
        usuario = usuario.copy(
            numeroCartao = cardNumber
        )
    }

    fun updateCardExpiration(cardExpiration: String) {
        usuario = usuario.copy(
            validadeCartao = cardExpiration
        )
    }

    fun updateCvv(cvv: String) {
        usuario = usuario.copy(
            cvv = cvv
        )
    }

    fun loginUser(
        email: String,
        senha: String,
        onSucesso: () -> Unit
    ) {
        if (authCarregando) return

        viewModelScope.launch {
            authCarregando = true
            authErroMensagem = null

            val resultadoRemoto = repositorioAutenticacao.login(email, senha)

            authCarregando = false

            if (resultadoRemoto.isSuccess) {
                val nome = repositorioAutenticacao.buscarNomeUsuarioAtual().getOrNull().orEmpty()
                usuario = usuario.copy(nome = nome, email = email)
                onSucesso()
            } else {
                authErroMensagem = resultadoRemoto.exceptionOrNull()?.message
                    ?: "E-mail ou senha inválidos"
            }
        }
    }

    fun registerUser(
        onSucesso: () -> Unit
    ) {
        if (authCarregando) return

        viewModelScope.launch {
            authCarregando = true
            authErroMensagem = null

            val resultado = repositorioAutenticacao.cadastrar(usuario)

            if (resultado.isSuccess) {
                authCarregando = false
                onSucesso()
            } else {
                authCarregando = false
                authErroMensagem = resultado.exceptionOrNull()?.message
                    ?: "Não foi possível concluir o cadastro"
            }
        }
    }

    fun resetPassword(
        email: String,
        onSucesso: () -> Unit
    ) {
        if (authCarregando) return

        viewModelScope.launch {
            authCarregando = true
            authErroMensagem = null

            val resultado = repositorioAutenticacao.redefinirSenha(email)

            authCarregando = false

            if (resultado.isSuccess) {
                onSucesso()
            } else {
                authErroMensagem = resultado.exceptionOrNull()?.message
                    ?: "Não foi possível redefinir a senha"
            }
        }
    }



}