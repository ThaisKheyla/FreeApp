package com.projeto.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freeapp.R
import com.example.freeapp.ui.theme.FreeAppTheme
import com.example.freeapp.ui.theme.PrimaryBlue
import com.example.freeapp.ui.theme.PrimaryWhite
import com.projeto.ui.components.BotaoVoltar
import com.projeto.ui.components.CampoTexto
import com.projeto.ui.viewmodel.UsuarioViewModel

@Composable
fun LoginScreen(
    viewModel: UsuarioViewModel = UsuarioViewModel(),
    onVoltar: () -> Unit = {}
) {
    val usuario = viewModel.usuario

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
                    onClick = onVoltar,
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

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Bem Vindo de volta !",
                color = PrimaryBlue,
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Column(modifier = Modifier.fillMaxSize().padding(
            start = 30.dp,
            end = 30.dp,
            top = 60.dp
            )) {
            CampoTexto(
                valor = usuario.email,
                rotulo = "E-mail",
                onValorChange = viewModel::atualizarEmail
            )

            CampoTexto(
                valor = usuario.senha,
                rotulo = "Senha",
                onValorChange = viewModel::atualizarSenha
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreenPreview() {
    FreeAppTheme {
        LoginScreen()
    }
}