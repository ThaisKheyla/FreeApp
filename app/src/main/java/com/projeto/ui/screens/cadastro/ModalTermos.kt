package com.projeto.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freeapp.R
import com.example.freeapp.ui.theme.PrimaryBlue
import com.example.freeapp.ui.theme.PrimaryWhite
import com.example.freeapp.ui.theme.fontColor

@Composable
fun ModalTermos(
    onFechar: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.Black.copy(alpha = 0.45f)
            ),
        contentAlignment = Alignment.BottomCenter
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f),

            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = PrimaryWhite
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .padding(24.dp),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    IconButton(
                        onClick = onFechar
                    ) {

                        Text("✕")
                    }
                }

                Image(
                    painter = painterResource(
                        id = R.drawable.img_termos
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp)
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = "Os Termos e a política de Privacidade do WhatsApp serão atualizados.",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Atualizações importantes",
                    color = PrimaryBlue,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Lei Geral de Proteção de Dados.",
                    color = fontColor,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Text(
                    text = """
Em caso de incidentes de segurança que possa gerar risco ou dano relevante para você ou qualquer um de nossos usuários/clientes, comunicaremos aos afetados e a Autoridade Nacional de Proteção de Dados sobre o ocorrido, em consonância com as disposições da Lei Geral de Proteção de Dados.

I – Quaisquer consequências decorrentes da negligência, imprudência ou imperícia dos usuários em relação a seus dados individuais.

Garantimos e nos responsabilizamos apenas da segurança dos processos de tratamento de dados e do cumprimento das finalidades descritas no presente instrumento.

Destacamos que a responsabilidade em relação à confidencialidade dos dados de acesso é do usuário.

II – Ações maliciosas de terceiros, como ataques de hackers, exceto se comprovada conduta culposa ou deliberada da empresa.

Destacamos que em caso de incidentes de segurança que possam gerar risco ou dano relevante para você ou qualquer um de nossos usuários/clientes, comunicaremos aos afetados e a Autoridade Nacional de Proteção de Dados sobre o ocorrido e cumpriremos as providências necessárias.

III – Inverdicidade das informações inseridas pelo usuário/cliente nos registros necessários para a utilização dos serviços.
        """.trimIndent(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = fontColor
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                BotaoBlue(
                    texto = "Concordar",
                    onClick = onFechar
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModalTermosPreview() {
    ModalTermos(onFechar = { })
}