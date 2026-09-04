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

    fun atualizarNome(nome: String) {
        usuario = usuario.copy(nome = nome)
    }

    fun atualizarDataNascimento(dataNascimento: String) {
        usuario = usuario.copy(dataNascimento = dataNascimento)
    }

    fun atualizarCpf(cpf: String) {
        usuario = usuario.copy(cpf = cpf)
    }

    fun atualizarEmail(email: String) {
        usuario = usuario.copy(email = email)
    }

    fun atualizarConfirmarEmail(confirmarEmail: String) {
        usuario = usuario.copy(confirmarEmail = confirmarEmail)
    }

    fun atualizarTelefone(telefone: String) {
        usuario = usuario.copy(telefone = telefone)
    }

    fun atualizarSenha(senha: String) {
        usuario = usuario.copy(senha = senha)
    }

    // ENDEREÇO pessoal

    fun atualizarCep(cep: String) {
        usuario = usuario.copy(cep = cep)
    }

    fun atualizarEndereco(endereco: String) {
        usuario = usuario.copy(endereco = endereco)
    }

    fun atualizarNumero(numero: String) {
        usuario = usuario.copy(numero = numero)
    }

    fun atualizarComplemento(complemento: String) {
        usuario = usuario.copy(complemento = complemento)
    }

    fun atualizarBairro(bairro: String) {
        usuario = usuario.copy(bairro = bairro)
    }

    fun atualizarCidade(cidade: String) {
        usuario = usuario.copy(cidade = cidade)
    }

    fun atualizarEstado(estado: String) {
        usuario = usuario.copy(estado = estado)
    }

    fun carregarEstadosIbge() {
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

    fun selecionarEstadoIbge(nomeEstado: String) {
        val estadoSelecionado = estadosIbge.firstOrNull { estado -> estado.nome == nomeEstado }

        atualizarEstado(nomeEstado)
        atualizarCidade("")
        cidadesIbge = emptyList()

        if (estadoSelecionado != null) {
            carregarCidadesIbge(estadoSelecionado.sigla)
        }
    }

    fun carregarCidadesIbge(uf: String) {
        if (ibgeCarregando) return

        viewModelScope.launch {
            ibgeCarregando = true
            ibgeErroMensagem = null

            val resultado = repositorioIbge.buscarCidades(uf)

            ibgeCarregando = false

            if (resultado.isSuccess) {
                cidadesIbge = resultado.getOrDefault(emptyList())
            } else {
                ibgeErroMensagem = resultado.exceptionOrNull()?.message
            }
        }
    }


    // DADOS PROFISSIONAIS

    fun atualizarProfissao(profissao: String) {
        usuario = usuario.copy(profissao = profissao)
    }


    fun atualizarEspecialidade(especialidade: String) {
        usuario = usuario.copy(especialidade = especialidade)
    }

    fun atualizarRegiao(regiao: String) {
        usuario = usuario.copy(regiao = regiao)
    }

    fun atualizarHorario(horario: String) {
        usuario = usuario.copy(horario = horario)
    }

    //Dados bancarios
    fun atualizarAgencia(agencia: String) {
        usuario = usuario.copy(agencia = agencia)
    }

    fun atualizarConta(conta: String) {
        usuario = usuario.copy(conta = conta)
    }

    fun atualizarTipoConta(tipoConta: String) {
        usuario = usuario.copy(tipoConta = tipoConta)
    }

    fun atualizarPix(pix: String) {
        usuario = usuario.copy(pix = pix)
    }

    fun atualizarOpcaoPagamento(opcaoPagamento: String) {
        usuario = usuario.copy(opcaoPagamento = opcaoPagamento)
    }

    //Pagamento
    fun atualizarNumeroCartao(numeroCartao: String) {
        usuario = usuario.copy(
            numeroCartao = numeroCartao
        )
    }

    fun atualizarValidadeCartao(validadeCartao: String) {
        usuario = usuario.copy(
            validadeCartao = validadeCartao
        )
    }

    fun atualizarCvv(cvv: String) {
        usuario = usuario.copy(
            cvv = cvv
        )
    }

    fun loginUsuario(
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

    fun registrarUsuario(
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

    fun redefinirSenha(
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