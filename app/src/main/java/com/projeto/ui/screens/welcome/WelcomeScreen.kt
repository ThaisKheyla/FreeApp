package com.projeto.ui.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freeapp.R
import com.example.freeapp.ui.theme.FreeAppTheme
import com.example.freeapp.ui.theme.WelcomeTitle
import com.projeto.ui.components.BotaoBlue
import com.projeto.ui.components.BotaoWhite

@Composable
fun WelcomeScreen() {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.img_inicial
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 60.dp),

                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Image(
                        painter = painterResource(
                            id = R.drawable.logo_free_blue
                        ),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .size(60.dp)
                    )

                    Text(
                        modifier = Modifier.padding(top = 60.dp),
                        text = "Bem Vindo!",
                        style = WelcomeTitle,
                        color = Color.White
                    )
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    BotaoBlue(
                        onClick = { },
                        texto = "CADASTRAR"
                    )

                    BotaoWhite(
                        onClick = { },
                        texto = "ENTRAR"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    FreeAppTheme {
        WelcomeScreen()
    }
}