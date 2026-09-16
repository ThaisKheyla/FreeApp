package com.example.freeapp.presentation.view.forgotPassword

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
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
import com.example.freeapp.presentation.viewmodel.AuthViewModel
import com.example.freeapp.presentation.viewmodel.contract.AuthEvent
import com.example.freeapp.presentation.viewmodel.previewAuthViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val authenticatedUser = uiState.authenticatedUser
    val savedPhone = authenticatedUser.phone
        .filter { character ->
            character.isDigit()
        }

    val authErrorMessage = uiState.errorMessage
    val isAuthLoading = uiState.isLoading

    var currentStep by remember {
        mutableStateOf(
            PasswordRecoveryStep.PHONE
        )
    }

    var previousCodeStep by remember {
        mutableStateOf(
            PasswordRecoveryStep.SMS_CODE
        )
    }

    var areaCode by remember(savedPhone) {
        mutableStateOf(
            savedPhone.take(2)
        )
    }

    var phoneNumber by remember(savedPhone) {
        mutableStateOf(
            savedPhone
                .drop(2)
                .take(9)
        )
    }

    var email by remember(authenticatedUser.email) {
        mutableStateOf(
            authenticatedUser.email
        )
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

    val isPhoneValid =
        areaCode.length == 2 &&
                phoneNumber.length >= 8

    val doPasswordsMatch =
        newPassword.isNotBlank() &&
                newPassword == confirmPassword

    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            if (event == AuthEvent.ResetPasswordSuccess) {
                currentStep = PasswordRecoveryStep.SUCCESS
            }
        }
    }

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(
                        horizontal = 14.dp
                    )
                    .padding(
                        top = 42.dp,
                        bottom = 148.dp
                    ),
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {
                BackButton(
                    onClick = {
                        if (
                            currentStep ==
                            PasswordRecoveryStep.PHONE
                        ) {
                            navController.popBackStack()
                        } else if (
                            currentStep ==
                            PasswordRecoveryStep.NEW_PASSWORD
                        ) {
                            currentStep = previousCodeStep
                        } else {
                            currentStep =
                                currentStep.previous()

                            code = ""
                        }
                    },
                    modifier = Modifier.align(
                        Alignment.Start
                    )
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                PasswordRecoveryHeader(
                    image = currentStep.image,
                    title = stringResource(
                        currentStep.titleRes
                    ),
                    description = stringResource(
                        currentStep.descriptionRes
                    )
                )

                Spacer(
                    modifier = Modifier.height(42.dp)
                )

                when (currentStep) {
                    PasswordRecoveryStep.PHONE -> {
                        PasswordRecoveryPhoneFields(
                            ddd = areaCode,
                            phoneNumber = phoneNumber,
                            onDddChange = { newAreaCode ->
                                areaCode = newAreaCode
                            },
                            onPhoneNumberChange = {
                                    newPhoneNumber ->

                                phoneNumber = newPhoneNumber
                            }
                        )
                    }

                    PasswordRecoveryStep.EMAIL -> {
                        TextField(
                            value = email,
                            label = stringResource(
                                R.string.common_email
                            ),
                            onValueChange = {
                                    newEmail ->

                                email = newEmail
                                viewModel.clearAuthState()
                            }
                        )
                    }

                    PasswordRecoveryStep.SMS_CODE,
                    PasswordRecoveryStep.EMAIL_CODE -> {
                        PasswordRecoveryCodeField(
                            code = code,
                            onCodeChange = { newCode ->
                                code = newCode
                            }
                        )

                        Spacer(
                            modifier = Modifier.height(18.dp)
                        )

                        PasswordRecoveryLink(
                            text = stringResource(
                                R.string.forgot_password_resend_code
                            ),
                            onClick = {
                                code = ""
                            }
                        )
                    }

                    PasswordRecoveryStep.NEW_PASSWORD -> {
                        PasswordField(
                            value = newPassword,
                            label = stringResource(
                                R.string.forgot_password_new_password
                            ),
                            onValueChange = {
                                    password ->

                                newPassword = password
                                viewModel.clearAuthState()
                            }
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        PasswordField(
                            value = confirmPassword,
                            label = stringResource(
                                R.string.forgot_password_confirm_password
                            ),
                            onValueChange = {
                                    passwordConfirmation ->

                                confirmPassword =
                                    passwordConfirmation

                                viewModel.clearAuthState()
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
                        style =
                            MaterialTheme.typography.bodySmall
                    )
                }
            }

            PasswordRecoveryFooter(
                buttonText = if (
                    currentStep ==
                    PasswordRecoveryStep.NEW_PASSWORD &&
                    isAuthLoading
                ) {
                    stringResource(
                        R.string.forgot_password_resetting
                    )
                } else {
                    stringResource(
                        currentStep.buttonTextRes
                    )
                },
                enabled = when (currentStep) {
                    PasswordRecoveryStep.PHONE -> {
                        isPhoneValid
                    }

                    PasswordRecoveryStep.EMAIL -> {
                        email.isNotBlank()
                    }

                    PasswordRecoveryStep.SMS_CODE,
                    PasswordRecoveryStep.EMAIL_CODE -> {
                        code.length == 4
                    }

                    PasswordRecoveryStep.NEW_PASSWORD -> {
                        doPasswordsMatch &&
                                !isAuthLoading
                    }

                    PasswordRecoveryStep.SUCCESS -> {
                        true
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        start = 14.dp,
                        end = 14.dp,
                        bottom = 22.dp
                    ),
                linkText = if (
                    currentStep ==
                    PasswordRecoveryStep.PHONE
                ) {
                    stringResource(
                        R.string.forgot_password_try_another_way
                    )
                } else {
                    null
                },
                onLinkClick = if (
                    currentStep ==
                    PasswordRecoveryStep.PHONE
                ) {
                    {
                        currentStep =
                            PasswordRecoveryStep.EMAIL

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
                            )
                        }

                        PasswordRecoveryStep.SMS_CODE,
                        PasswordRecoveryStep.EMAIL_CODE -> {
                            previousCodeStep = currentStep

                            currentStep =
                                PasswordRecoveryStep.NEW_PASSWORD
                        }

                        PasswordRecoveryStep.NEW_PASSWORD -> {
                            val recoveryEmail =
                                email.ifBlank {
                                authenticatedUser.email
                                }

                            viewModel.resetPassword(
                                email = recoveryEmail
                            )
                        }

                        PasswordRecoveryStep.SUCCESS -> {
                            navController.navigate(
                                Routes.LOGIN
                            ) {
                                popUpTo(
                                    Routes.LOGIN
                                )

                                launchSingleTop = true
                            }
                        }
                    }
                }
            )
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(
        navController = androidx.navigation.compose.rememberNavController(),
        viewModel = previewAuthViewModel()
    )
}