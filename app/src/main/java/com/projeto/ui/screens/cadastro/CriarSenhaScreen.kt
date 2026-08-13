package com.projeto.ui.screens.cadastro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.freeapp.ui.theme.PrimaryBlue
import com.example.freeapp.ui.theme.TextFieldBackground
import com.projeto.ui.components.BotaoBlueFixo
import com.projeto.ui.components.BotaoVoltar
import com.projeto.ui.navigation.Routes
import com.projeto.ui.viewmodel.UsuarioViewModel

@Composable
fun CriarSenhaScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsuarioViewModel = UsuarioViewModel()
) {
    val usuario = viewModel.usuario
    var confirmarSenha by remember { mutableStateOf("") }
    val senhasValidas = usuario.senha.isNotBlank() && usuario.senha == confirmarSenha

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
                    text = "Crie uma senha",
                    style = MaterialTheme.typography.headlineLarge
                )

                CampoSenha(
                    valor = usuario.senha,
                    rotulo = "Crie uma senha",
                    onValorChange = viewModel::atualizarSenha
                )

                CampoSenha(
                    valor = confirmarSenha,
                    rotulo = "Confirme sua Senha",
                    onValorChange = { senha -> confirmarSenha = senha },
                    isError = confirmarSenha.isNotBlank() && confirmarSenha != usuario.senha
                )
            }

            BotaoBlueFixo(
                texto = "FINALIZAR",
                enabled = senhasValidas,
                onClick = {
                    navController.navigate(Routes.WELCOME) {
                        popUpTo(Routes.WELCOME)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
private fun CampoSenha(
    valor: String,
    rotulo: String,
    onValorChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false
) {
    var senhaVisivel by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = valor,
        onValueChange = onValorChange,
        placeholder = {
            Text(
                text = rotulo,
                style = MaterialTheme.typography.bodySmall
            )
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    senhaVisivel = !senhaVisivel
                }
            ) {
                Icon(
                    imageVector = if (senhaVisivel) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    contentDescription = if (senhaVisivel) "Ocultar senha" else "Mostrar senha",
                    tint = Color(0xFF1F2937)
                )
            }
        },
        isError = isError,
        visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = RoundedCornerShape(10.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (isError) Color.Red else Color.Transparent,
            unfocusedBorderColor = if (isError) Color.Red else Color.Transparent,
            focusedContainerColor = TextFieldBackground,
            unfocusedContainerColor = TextFieldBackground,
            cursorColor = PrimaryBlue
        )
    )
}