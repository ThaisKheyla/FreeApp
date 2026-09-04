package com.example.freeapp.presentation.screens.cadastro

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.components.FixedBlueButton
import com.example.freeapp.presentation.components.PasswordField
import com.example.freeapp.presentation.viewmodel.UsuarioViewModel

@Composable
fun CriarSenhaScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
        viewModel: UsuarioViewModel = UsuarioViewModel()
) {
    val usuario = viewModel.usuario
    val mensagemErroAuth = viewModel.authErroMensagem
    val carregandoAuth = viewModel.authCarregando
    var confirmarSenha by remember { mutableStateOf("") }
    val senhasValidas =
        usuario.senha.isNotBlank() &&
            usuario.senha == confirmarSenha &&
            !carregandoAuth

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
                    text = "Crie uma senha",
                    style = MaterialTheme.typography.headlineLarge
                )

                PasswordField(
                    value = usuario.senha,
                    label = "Crie uma senha",
                    onValueChange = {
                        viewModel.atualizarSenha(it)
                        viewModel.limparEstadoAuth()
                    }
                )

                PasswordField(
                    value = confirmarSenha,
                    label = "Confirme sua Senha",
                    onValueChange = { senha ->
                        confirmarSenha = senha
                        viewModel.limparEstadoAuth()
                    },
                    isError = confirmarSenha.isNotBlank() && confirmarSenha != usuario.senha
                )

                if (mensagemErroAuth != null) {
                    Text(
                        text = mensagemErroAuth,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            FixedBlueButton(
                text = if (carregandoAuth) "FINALIZANDO..." else "FINALIZAR",
                enabled = senhasValidas,
                onClick = {
                    viewModel.registrarUsuario {
                        navController.navigate(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.LOGIN) {
                            popUpTo(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.LOGIN)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CriarSenhaScreenPreview() {
    CriarSenhaScreen(navController = rememberNavController())
}