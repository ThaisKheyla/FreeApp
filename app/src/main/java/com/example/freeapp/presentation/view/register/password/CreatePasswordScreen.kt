package com.example.freeapp.presentation.view.register.password

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.R
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.components.FixedBlueButton
import com.example.freeapp.presentation.components.PasswordField
import com.example.freeapp.presentation.navigation.Routes
import com.example.freeapp.presentation.viewmodel.CreatePasswordViewModel
import com.example.freeapp.presentation.viewmodel.contract.RegistrationEvent
import com.example.freeapp.presentation.viewmodel.previewRegistrationViewModel

@Composable
fun CreatePasswordScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CreatePasswordViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val user = uiState.user
    val authErrorMessage = uiState.authErrorMessage
    val isAuthLoading = uiState.isAuthLoading

    var confirmPassword by remember {
        mutableStateOf("")
    }

    LaunchedEffect(viewModel) {
        viewModel.events.collect { event ->
            if (event == RegistrationEvent.RegistrationSuccess) {
                navController.navigate(
                    Routes.LOGIN
                ) {
                    popUpTo(Routes.LOGIN)
                    launchSingleTop = true
                }
            }
        }
    }

    val arePasswordsValid =
        user.password.isNotBlank() &&
                user.password == confirmPassword &&
                !isAuthLoading

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
                    text = stringResource(
                        R.string.create_password_title
                    ),
                    style = MaterialTheme.typography.headlineLarge
                )

                PasswordField(
                    value = user.password,
                    label = stringResource(
                        R.string.create_password_label
                    ),
                    onValueChange = { password ->
                        viewModel.updatePassword(password)
                        viewModel.clearAuthState()
                    }
                )

                PasswordField(
                    value = confirmPassword,
                    label = stringResource(
                        R.string.create_password_confirm_label
                    ),
                    onValueChange = { passwordConfirmation ->
                        confirmPassword = passwordConfirmation
                        viewModel.clearAuthState()
                    },
                    isError =
                        confirmPassword.isNotBlank() &&
                                confirmPassword != user.password
                )

                if (authErrorMessage != null) {
                    Text(
                        text = authErrorMessage,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            FixedBlueButton(
                text = if (isAuthLoading) {
                    stringResource(
                        R.string.create_password_finishing
                    )
                } else {
                    stringResource(
                        R.string.create_password_finish
                    )
                },
                enabled = arePasswordsValid,
                onClick = {
                    viewModel.registerUser()
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CreatePasswordScreenPreview() {
    val registrationViewModel = previewRegistrationViewModel()
    CreatePasswordScreen(
        navController = rememberNavController(),
        viewModel = CreatePasswordViewModel(registrationViewModel)
    )
}