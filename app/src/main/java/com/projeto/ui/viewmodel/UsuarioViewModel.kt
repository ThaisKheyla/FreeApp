package com.projeto.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projeto.data.remote.ClienteRetrofit
import com.projeto.data.repository.RepositorioAutenticacao
import com.projeto.domain.model.Usuario
import kotlinx.coroutines.launch

class UsuarioViewModel : ViewModel() {
    private val repositorioAutenticacao = RepositorioAutenticacao(ClienteRetrofit.servicoAutenticacao)

    //dados pessoais
    var usuario by mutableStateOf(
        Usuario()
    )
        private set

    var authCarregando by mutableStateOf(false)
        private set

    var authErroMensagem by mutableStateOf<String?>(null)
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
                onSucesso()
                return@launch
            }

            // Fallback local para ambiente sem backend pronto.
            val emailCorreto = email.trim().equals(
                usuario.email.trim(),
                ignoreCase = true
            )
            val senhaCorreta = senha == usuario.senha

            if (emailCorreto && senhaCorreta) {
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

            authCarregando = false

            if (resultado.isSuccess) {
                onSucesso()
            } else {
                authErroMensagem = resultado.exceptionOrNull()?.message
                    ?: "Não foi possível concluir o cadastro"
            }
        }
    }

    fun redefinirSenha(
        email: String,
        novaSenha: String,
        onSucesso: () -> Unit
    ) {
        if (authCarregando) return

        viewModelScope.launch {
            authCarregando = true
            authErroMensagem = null

            val resultado = repositorioAutenticacao.redefinirSenha(email, novaSenha)

            authCarregando = false

            if (resultado.isSuccess) {
                atualizarSenha(novaSenha)
                onSucesso()
            } else {
                authErroMensagem = resultado.exceptionOrNull()?.message
                    ?: "Não foi possível redefinir a senha"
            }
        }
    }



}