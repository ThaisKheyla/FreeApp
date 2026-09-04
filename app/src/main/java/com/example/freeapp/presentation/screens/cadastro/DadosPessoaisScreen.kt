package com.example.freeapp.presentation.screens.cadastro

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.presentation.theme.CheckboxBackground
import com.example.freeapp.presentation.theme.PrimaryBlue
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.components.FixedBlueButton
import com.example.freeapp.presentation.components.InputType
import com.example.freeapp.presentation.components.TextField
import com.example.freeapp.presentation.navigation.Routes
import com.example.freeapp.presentation.validation.UsuarioValidator
import com.example.freeapp.presentation.viewmodel.UsuarioViewModel

@Composable
fun DadosPessoaisScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
        viewModel: com.example.freeapp.presentation.viewmodel.UsuarioViewModel = UsuarioViewModel()
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

            BackButton(
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
            TextField(
                value = usuario.nome,
                label = "Nome completo",
                onValueChange = viewModel::updateName,
                inputType = InputType.LETTERS_ONLY,
                showCheck = nomeValido && usuario.nome.isNotBlank(),
                isError = usuario.nome.isNotBlank() && !nomeValido
            )

            TextField(
                value = usuario.dataNascimento,
                label = "Data de nascimento",
                onValueChange = viewModel::updateBirthDate,
                inputType = InputType.NUMBERS_AND_SLASH,
                showCheck = dataNascimentoValida && usuario.dataNascimento.isNotBlank(),
                isError = usuario.dataNascimento.isNotBlank() && !dataNascimentoValida
            )

            TextField(
                value = usuario.cpf,
                label = "CPF",
                onValueChange = viewModel::updateCpf,
                inputType = InputType.NUMBERS_ONLY,
                showCheck = cpfValido &&
                        usuario.cpf.isNotBlank(),

                isError = usuario.cpf.isNotBlank() &&
                        !cpfValido
            )

            TextField(
                value = usuario.email,
                label = "E-mail",
                onValueChange = viewModel::updateEmail,
                showCheck = emailValido && usuario.email.isNotBlank(),
                isError = usuario.email.isNotBlank() && !emailValido
            )

            TextField(
                value = usuario.confirmarEmail,
                label = "Confirme seu e-mail",
                onValueChange = viewModel::updateConfirmEmail,
                showCheck = emailsIguais &&
                        usuario.confirmarEmail.isNotBlank(),

                isError = usuario.confirmarEmail.isNotBlank() &&
                        !emailsIguais
            )

            TextField(
                value = usuario.telefone,
                label = "Número com DD",
                onValueChange = viewModel::updatePhone,
                inputType = InputType.NUMBERS_ONLY,
                showCheck = telefoneValido &&
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

            FixedBlueButton(
                text = "CONTINUAR",
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DadosPessoaisScreenPreview() {
    DadosPessoaisScreen(navController = rememberNavController())
}
