package com.example.freeapp.presentation.view.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.R
import com.example.freeapp.presentation.theme.PrimaryBlue
import com.example.freeapp.presentation.theme.PrimaryWhite
import com.example.freeapp.presentation.components.BlueButton
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.components.PasswordField
import com.example.freeapp.presentation.components.TextField
import com.example.freeapp.presentation.navigation.Routes
import com.example.freeapp.presentation.viewmodel.AuthViewModel
import com.example.freeapp.presentation.viewmodel.previewAuthViewModel
import androidx.compose.ui.res.stringResource

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel
) {
    val usuario = viewModel.authenticatedUser

    var emailLogin by remember(usuario.email) {
        mutableStateOf(usuario.email)
    }

    var senhaLogin by remember {
        mutableStateOf("")
    }

    val mensagemErroAuth = viewModel.authErrorMessage
    val carregandoAuth = viewModel.authLoading

    val camposPreenchidos =
        emailLogin.isNotBlank() &&
                senhaLogin.isNotBlank() &&
                !carregandoAuth

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .clip(
                    RoundedCornerShape(
                        bottomStartPercent = 30,
                        bottomEndPercent = 30
                    )
                )
                .background(PrimaryBlue)
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        start = 24.dp,
                        top = 24.dp
                    )
            ) {
                BackButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    tint = PrimaryWhite
                )
            }

            Image(
                painter = painterResource(
                    id = R.drawable.logo_free_white
                ),
                contentDescription = stringResource(
                    R.string.login_logo_description
                ),
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.Center)
                    .offset(y = (-10).dp)
            )
        }

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Text(
            text = stringResource(
                R.string.login_welcome_back
            ),
            color = PrimaryBlue,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(
                Alignment.CenterHorizontally
            )
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {

            TextField(
                value = emailLogin,
                label = stringResource(
                    R.string.common_email
                ),
                onValueChange = { email ->
                    emailLogin = email
                    viewModel.clearAuthState()
                },
                isError = mensagemErroAuth != null
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            PasswordField(
                value = senhaLogin,
                label = stringResource(
                    R.string.common_password
                ),
                onValueChange = { senha ->
                    senhaLogin = senha
                    viewModel.clearAuthState()
                },
                isError = mensagemErroAuth != null
            )

            if (mensagemErroAuth != null) {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = mensagemErroAuth,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = stringResource(
                    R.string.login_forgot_password
                ),
                color = PrimaryBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable {
                        navController.navigate(
                            Routes.ESQUECI_SENHA
                        )
                    }
            )

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            BlueButton(
                text = if (carregandoAuth)
                    stringResource(R.string.login_loading)
                else
                    stringResource(R.string.common_login),

                enabled = camposPreenchidos,

                onClick = {
                    viewModel.loginUser(
                        email = emailLogin,
                        senha = senhaLogin
                    ) {
                        navController.navigate(
                            Routes.HOME
                        )
                    }
                }
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(
                        R.string.login_new_user
                    )
                )

                Text(
                    text = stringResource(
                        R.string.login_register
                    ),
                    color = Color(0xFF0451FF),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate(
                            Routes.PERSONAL_DATA
                        )
                    }
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        navController = rememberNavController(),
        viewModel = previewAuthViewModel()
    )
}