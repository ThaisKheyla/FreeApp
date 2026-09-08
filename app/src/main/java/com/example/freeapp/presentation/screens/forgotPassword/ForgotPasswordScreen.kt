package com.example.freeapp.presentation.screens.forgotPassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.freeapp.R
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.components.PasswordField
import com.example.freeapp.presentation.components.PasswordRecoveryCodeField
import com.example.freeapp.presentation.components.PasswordRecoveryFooter
import com.example.freeapp.presentation.components.PasswordRecoveryHeader
import com.example.freeapp.presentation.components.PasswordRecoveryLink
import com.example.freeapp.presentation.components.PasswordRecoveryPhoneFields
import com.example.freeapp.presentation.components.TextField
import com.example.freeapp.presentation.navigation.Routes
import com.example.freeapp.presentation.viewmodel.UsuarioViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsuarioViewModel = UsuarioViewModel()
) {
    val savedPhoneNumber = viewModel.usuario.telefone
        .filter { character ->
            character.isDigit()
        }

    val authErrorMessage = viewModel.authErroMensagem
    val isAuthLoading = viewModel.authCarregando

    var currentStep by remember {
        mutableStateOf(PasswordRecoveryStep.PHONE)
    }

    var previousCodeStep by remember {
        mutableStateOf(PasswordRecoveryStep.SMS_CODE)
    }

    var areaCode by remember(savedPhoneNumber) {
        mutableStateOf(savedPhoneNumber.take(2))
    }

    var phoneNumber by remember(savedPhoneNumber) {
        mutableStateOf(
            savedPhoneNumber
                .drop(2)
                .take(9)
        )
    }

    var email by remember(viewModel.usuario.email) {
        mutableStateOf(viewModel.usuario.email)
    }

    var code by remember {
        mutableStateOf("")
    }

    var newPassword by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    val isPhoneNumberValid =
        areaCode.length == 2 &&
                phoneNumber.length >= 8

    val doPasswordsMatch =
        newPassword.isNotBlank() &&
                newPassword == confirmPassword

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
                    .padding(horizontal = 14.dp)
                    .padding(
                        top = 42.dp,
                        bottom = 148.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BackButton(
                    onClick = {
                        if (
                            currentStep == PasswordRecoveryStep.PHONE
                        ) {
                            navController.popBackStack()
                        } else if (
                            currentStep == PasswordRecoveryStep.NEW_PASSWORD
                        ) {
                            currentStep = previousCodeStep
                        } else {
                            currentStep = currentStep.previous()
                            code = ""
                        }
                    },
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                PasswordRecoveryHeader(
                    image = currentStep.image,
                    title = currentStep.title,
                    description = currentStep.description
                )

                Spacer(
                    modifier = Modifier.height(42.dp)
                )

                when (currentStep) {
                    PasswordRecoveryStep.PHONE -> {
                        PasswordRecoveryPhoneFields(
                            ddd = areaCode,
                            phoneNumber = phoneNumber,
                            onDddChange = {
                                areaCode = it
                            },
                            onPhoneNumberChange = {
                                phoneNumber = it
                            }
                        )
                    }

                    PasswordRecoveryStep.EMAIL -> {
                        TextField(
                            value = email,
                            label = "E-mail",
                            onValueChange = {
                                email = it
                                viewModel.limparEstadoAuth()
                            }
                        )
                    }

                    PasswordRecoveryStep.SMS_CODE,
                    PasswordRecoveryStep.EMAIL_CODE -> {
                        PasswordRecoveryCodeField(
                            code = code,
                            onCodeChange = {
                                code = it
                            }
                        )

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        PasswordRecoveryLink(
                            text = "Não recebeu um código? Reenviar",
                            onClick = {
                                code = ""
                            }
                        )
                    }

                    PasswordRecoveryStep.NEW_PASSWORD -> {
                        PasswordField(
                            value = newPassword,
                            label = "Nova Senha",
                            onValueChange = {
                                newPassword = it
                                viewModel.limparEstadoAuth()
                            }
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        PasswordField(
                            value = confirmPassword,
                            label = "Confirme sua Senha",
                            onValueChange = {
                                confirmPassword = it
                                viewModel.limparEstadoAuth()
                            },
                            isError =
                                confirmPassword.isNotBlank() &&
                                        confirmPassword != newPassword
                        )
                    }

                    PasswordRecoveryStep.SUCCESS -> Unit
                }

                if (authErrorMessage != null) {
                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = authErrorMessage,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            PasswordRecoveryFooter(
                buttonText = if (
                    currentStep == PasswordRecoveryStep.NEW_PASSWORD &&
                    isAuthLoading
                ) {
                    "REDEFININDO..."
                } else {
                    currentStep.buttonText
                },
                enabled = when (currentStep) {
                    PasswordRecoveryStep.PHONE ->
                        isPhoneNumberValid

                    PasswordRecoveryStep.EMAIL ->
                        email.isNotBlank()

                    PasswordRecoveryStep.SMS_CODE,
                    PasswordRecoveryStep.EMAIL_CODE ->
                        code.length == 4

                    PasswordRecoveryStep.NEW_PASSWORD ->
                        doPasswordsMatch && !isAuthLoading

                    PasswordRecoveryStep.SUCCESS ->
                        true
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        start = 14.dp,
                        end = 14.dp,
                        bottom = 22.dp
                    ),
                linkText = if (
                    currentStep == PasswordRecoveryStep.PHONE
                ) {
                    "TENTE DE OUTRA MANEIRA"
                } else {
                    null
                },
                onLinkClick = if (
                    currentStep == PasswordRecoveryStep.PHONE
                ) {
                    {
                        currentStep = PasswordRecoveryStep.EMAIL
                        code = ""
                    }
                } else {
                    null
                },
                onButtonClick = {
                    when (currentStep) {
                        PasswordRecoveryStep.PHONE -> {
                            currentStep =
                                PasswordRecoveryStep.SMS_CODE
                        }

                        PasswordRecoveryStep.EMAIL -> {
                            viewModel.resetPassword(
                                email = email
                            ) {
                                currentStep =
                                    PasswordRecoveryStep.SUCCESS
                            }
                        }

                        PasswordRecoveryStep.SMS_CODE,
                        PasswordRecoveryStep.EMAIL_CODE -> {
                            previousCodeStep = currentStep
                            currentStep =
                                PasswordRecoveryStep.NEW_PASSWORD
                        }

                        PasswordRecoveryStep.NEW_PASSWORD -> {
                            val recoveryEmail = email.ifBlank {
                                viewModel.usuario.email
                            }

                            viewModel.resetPassword(
                                email = recoveryEmail
                            ) {
                                currentStep =
                                    PasswordRecoveryStep.SUCCESS
                            }
                        }

                        PasswordRecoveryStep.SUCCESS -> {
                            navController.navigate(Routes.LOGIN) {
                                popUpTo(Routes.LOGIN)
                                launchSingleTop = true
                            }
                        }
                    }
                }
            )
        }
    }
}

private enum class PasswordRecoveryStep(
    val title: String,
    val description: String,
    val buttonText: String,
    val image: Int
) {
    PHONE(
        title = "Esqueceu a Senha?",
        description = "Não se preocupe, nós vamos te ajudar! Informe o número para o qual deseja redefinir a sua senha.",
        buttonText = "CONTINUAR",
        image = R.drawable.illustration
    ),

    EMAIL(
        title = "Esqueci minha senha E-mail",
        description = "Informe seu e-mail para receber o código de redefinição.",
        buttonText = "CONTINUAR",
        image = R.drawable.illustration
    ),

    SMS_CODE(
        title = "Verifique seu celular",
        description = "Acabamos de enviar um código para o seu número de telefone.",
        buttonText = "VERIFICAR",
        image = R.drawable.illustration__1_
    ),

    EMAIL_CODE(
        title = "Verifique seu E-mail",
        description = "Acabamos de enviar um código para o seu e-mail.",
        buttonText = "VERIFICAR",
        image = R.drawable.illustration__1_
    ),

    NEW_PASSWORD(
        title = "Redefina sua senha",
        description = "Preencha os campos abaixo.",
        buttonText = "REDEFINIR SENHA",
        image = R.drawable.illustration__2_
    ),

    SUCCESS(
        title = "Redefinido com sucesso",
        description = "Agora você pode fazer login na sua conta.",
        buttonText = "CONECTE-SE AGORA",
        image = R.drawable.illustration__3_
    );

    fun previous(): PasswordRecoveryStep {
        return when (this) {
            PHONE -> PHONE
            EMAIL -> PHONE
            SMS_CODE -> PHONE
            EMAIL_CODE -> EMAIL
            NEW_PASSWORD -> SMS_CODE
            SUCCESS -> NEW_PASSWORD
        }
    }
}