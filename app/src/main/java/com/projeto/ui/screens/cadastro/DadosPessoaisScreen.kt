package com.projeto.ui.screens.cadastro

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.freeapp.ui.theme.CheckboxBackground
import com.example.freeapp.ui.theme.PrimaryBlue
import com.projeto.ui.components.BotaoBlueFixo
import com.projeto.ui.components.BotaoVoltar
import com.projeto.ui.components.CampoTexto
import com.projeto.ui.navigation.Routes
import com.projeto.ui.viewmodel.UsuarioViewModel
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.projeto.ui.components.ModalTermos
import com.projeto.ui.validation.UsuarioValidator

@Composable
fun DadosPessoaisScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsuarioViewModel = UsuarioViewModel()
) {
    val usuario = viewModel.usuario
    var mostrarTermos by remember {
        mutableStateOf(false)
    }
    val cpfValido =
        UsuarioValidator.cpfValido(usuario.cpf)

    val emailValido =
        UsuarioValidator.emailValido(usuario.email)

    val emailsIguais =
        UsuarioValidator.emailsIguais(
            usuario.email,
            usuario.confirmarEmail
        )

    val telefoneValido =
        UsuarioValidator.telefoneValido(
            usuario.telefone
        )

    val nomeValido =
        UsuarioValidator.nomeValido(usuario.nome)

    val dataNascimentoValida =
        UsuarioValidator.dataValida(usuario.dataNascimento)

    var aceitouTermos by remember {
        mutableStateOf(false)
    }

    val dadosPessoaisValidos =
        nomeValido &&
                dataNascimentoValida &&
                cpfValido &&
                emailValido &&
                emailsIguais &&
                telefoneValido &&
                aceitouTermos

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
                    .padding(bottom = 96.dp),
                verticalArrangement = Arrangement.spacedBy(22.dp)
            ) {

            BotaoVoltar(
                onClick = {
                    navController.popBackStack()
                }
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

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
                mostrarCheck = cpfValido &&
                        usuario.cpf.isNotBlank(),

                isError = usuario.cpf.isNotBlank() &&
                        !cpfValido
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
                mostrarCheck = emailsIguais &&
                        usuario.confirmarEmail.isNotBlank(),

                isError = usuario.confirmarEmail.isNotBlank() &&
                        !emailsIguais
            )

            CampoTexto(
                valor = usuario.telefone,
                rotulo = "Número com DD",
                onValorChange = viewModel::atualizarTelefone,
                mostrarCheck = telefoneValido &&
                        usuario.telefone.isNotBlank(),

                isError = usuario.telefone.isNotBlank() &&
                        !telefoneValido
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

                Spacer(
                    modifier = Modifier.width(4.dp)
                )

                val annotatedText = buildAnnotatedString {

                    append("Li e concordo com os ")

                    pushStringAnnotation(
                        tag = "TERMS",
                        annotation = "termos"
                    )

                    withStyle(
                        SpanStyle(
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Normal
                        )
                    )

                    {
                        append("Termo de Uso e Política de Privacidade")
                    }

                    pop()
                }

                ClickableText(
                    text = annotatedText,
                    style = MaterialTheme.typography.labelMedium,
                    onClick = { offset ->

                        annotatedText
                            .getStringAnnotations(
                                tag = "TERMS",
                                start = offset,
                                end = offset
                            )
                            .firstOrNull()
                            ?.let {
                                mostrarTermos = true
                            }
                    }
                )
            }

            }

            BotaoBlueFixo(
                texto = "CONTINUAR",
                enabled = dadosPessoaisValidos,
                onClick = {
                    navController.navigate(Routes.ENDERECOS)
                }
            )
            if (mostrarTermos) {
                ModalTermos(
                    onFechar = {
                        mostrarTermos = false
                    }
                )
            }
        }
    }
}
