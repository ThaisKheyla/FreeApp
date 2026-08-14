package com.projeto.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freeapp.ui.theme.PrimaryWhite

@Composable
fun AlertModal(
    titulo: String,
    mensagem: String,
    botaoTexto: String,
    corDestaque: Color,
    icone: ImageVector,
    onConfirmar: () -> Unit,
    onFechar: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.35f)),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Fechar",
                        modifier = Modifier.clickable {
                            onFechar()
                        }
                    )
                }

                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = corDestaque
                    )
                ) {

                    Box(
                        modifier = Modifier.size(72.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            imageVector = icone,
                            contentDescription = null,
                            tint = PrimaryWhite,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = mensagem,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )

                Spacer(
                    modifier = Modifier.height(32.dp)
                )

                Button(
                    onClick = onConfirmar,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = corDestaque
                    )
                ) {

                    Text(
                        text = botaoTexto,
                        color = PrimaryWhite
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFBDBDBD
)
@Composable
fun AlertModalErroPreview() {

    AlertModal(
        titulo = "Desculpe!",
        mensagem = "Ocorreu um erro.\nTente novamente.",
        botaoTexto = "OK",
        corDestaque = Color(0xFFFF3131),
        icone = Icons.Default.Close,
        onConfirmar = {},
        onFechar = {}
    )
}