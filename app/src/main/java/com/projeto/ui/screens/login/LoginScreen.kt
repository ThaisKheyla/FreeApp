package com.projeto.ui.screens

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
import com.example.freeapp.ui.theme.FreeAppTheme
import com.example.freeapp.ui.theme.PrimaryBlue
import com.example.freeapp.ui.theme.PrimaryWhite
import com.projeto.ui.components.BotaoBlue
import com.projeto.ui.components.BotaoBlueFixo
import com.projeto.ui.components.BotaoVoltar
import com.projeto.ui.components.CampoSenha
import com.projeto.ui.components.CampoTexto
import com.projeto.ui.navigation.Routes
import com.projeto.ui.viewmodel.UsuarioViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: UsuarioViewModel = UsuarioViewModel()
) {
    val usuario = viewModel.usuario
    var emailLogin by remember(usuario.email) { mutableStateOf(usuario.email) }
    var senhaLogin by remember { mutableStateOf("") }
    val mensagemErroAuth = viewModel.authErroMensagem
    val carregandoAuth = viewModel.authCarregando
    val camposPreenchidos = emailLogin.isNotBlank() && senhaLogin.isNotBlank() && !carregandoAuth

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
                BotaoVoltar(
                    onClick = { navController.popBackStack() },
                    tint = PrimaryWhite
                )
            }

            Image(
                painter = painterResource(
                    id = R.drawable.logo_free_white
                ),
                contentDescription = "Logo Free",
                modifier = Modifier
                    .size(110.dp)
                    .align(Alignment.Center)
                    .offset(y = (-10).dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Bem-vindo de volta!",
            color = PrimaryBlue,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {

            CampoTexto(
                valor = emailLogin,
                rotulo = "E-mail",
                onValorChange = { email ->
                    emailLogin = email
                    viewModel.limparEstadoAuth()
                },
                isError = mensagemErroAuth != null
            )

            Spacer(modifier = Modifier.height(24.dp))

            CampoSenha(
                valor = senhaLogin,
                rotulo = "Senha",
                onValorChange = { senha ->
                    senhaLogin = senha
                    viewModel.limparEstadoAuth()
                },
                isError = mensagemErroAuth != null
            )

            if (mensagemErroAuth != null) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = mensagemErroAuth,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Esqueci a senha",
                color = PrimaryBlue,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable {
                        navController.navigate(Routes.ESQUECI_SENHA)
                    }
            )

            Spacer(modifier = Modifier.height(40.dp))

            BotaoBlue(
                texto = if (carregandoAuth) "ENTRANDO..." else "ENTRAR",
                enabled = camposPreenchidos,
                onClick = {
                    viewModel.loginUsuario(
                        email = emailLogin,
                        senha = senhaLogin
                    ) {
                        navController.navigate(Routes.HOME)
                    }
                }
            )


            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Novo Usuário? "
                )

                Text(
                    text = "Cadastre-se",
                    color = Color(0xFF0451FF),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.DADOS_PESSOAIS)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}
