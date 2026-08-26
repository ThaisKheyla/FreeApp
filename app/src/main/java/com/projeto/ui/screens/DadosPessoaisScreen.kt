
package com.projeto.ui.screens

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projeto.ui.components.CampoTexto
import com.projeto.ui.viewmodel.UsuarioViewModel
import com.projeto.ui.components.BotaoVoltar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import com.example.freeapp.ui.theme.CheckboxBackground
import com.example.freeapp.ui.theme.PrimaryBlue
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.freeapp.ui.theme.FreeAppTheme
import com.projeto.ui.components.BotaoBlueFixo
import com.projeto.ui.validation.UsuarioValidator

@Composable
fun DadosPessoaisScreen(
    modifier: Modifier = Modifier,
    viewModel: UsuarioViewModel = UsuarioViewModel()
) {
    val usuario = viewModel.usuario
    var aceitouTermos by remember {
        mutableStateOf(false)
    }
    val nomeValido = UsuarioValidator.nomeValido(usuario.nome)
    val dataNascimentoValida = UsuarioValidator.dataValida(usuario.dataNascimento)
    val cpfValido = UsuarioValidator.cpfValido(usuario.cpf)
    val emailValido = UsuarioValidator.emailValido(usuario.email)
    val telefoneValido = UsuarioValidator.telefoneValido(usuario.telefone)
    val dadosPessoaisValidos =
        nomeValido &&
                dataNascimentoValida &&
                cpfValido &&
                emailValido &&
                telefoneValido &&
                aceitouTermos

    Surface(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
                    .padding(bottom = 96.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
            BotaoVoltar(
                onClick = { }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Dados Pessoais",
                style = MaterialTheme.typography.headlineLarge
            )
            CampoTexto(
                valor = usuario.nome,
                rotulo = "Nome completo",
                onValorChange = viewModel::atualizarNome,
                mostrarCheck = nomeValido && usuario.nome.isNotBlank(),
                isError = usuario.nome.isNotBlank() && !nomeValido
            )
            CampoTexto(
                valor = usuario.dataNascimento,
                rotulo = "Data de nascimento",
                onValorChange = viewModel::atualizarDataNascimento,
                mostrarCheck = dataNascimentoValida && usuario.dataNascimento.isNotBlank(),
                isError = usuario.dataNascimento.isNotBlank() && !dataNascimentoValida
            )
            CampoTexto(
                valor = usuario.cpf,
                rotulo = "CPF",
                onValorChange = viewModel::atualizarCpf,
                mostrarCheck = cpfValido && usuario.cpf.isNotBlank(),
                isError = usuario.cpf.isNotBlank() && !cpfValido
            )
            CampoTexto(
                valor = usuario.email,
                rotulo = "E-mail",
                onValorChange = viewModel::atualizarEmail,
                mostrarCheck = emailValido && usuario.email.isNotBlank(),
                isError = usuario.email.isNotBlank() && !emailValido
            )
            CampoTexto(
                valor = usuario.confirmarEmail,
                rotulo = "Confirme seu e-mail",
                onValorChange = viewModel::atualizarConfirmarEmail,
            )
            CampoTexto(
                valor = usuario.telefone,
                rotulo = "Telefone",
                onValorChange = viewModel::atualizarTelefone,
                mostrarCheck = telefoneValido && usuario.telefone.isNotBlank(),
                isError = usuario.telefone.isNotBlank() && !telefoneValido
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    modifier = Modifier.size(20.dp),
                    checked = aceitouTermos,
                    onCheckedChange = {
                        aceitouTermos = it
                    },
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = CheckboxBackground,
                        checkedColor = PrimaryBlue
                    )
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Li e estou de acordo com o Termo de Uso e Política de Privacidade",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            }


            BotaoBlueFixo(
                texto = "CONTINUAR",
                enabled = dadosPessoaisValidos,
                onClick = { }
            )

        }
    }
}

