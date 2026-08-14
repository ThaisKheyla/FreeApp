package com.projeto.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ModalTermos(
    onFechar: () -> Unit
) {

    AlertDialog(
        onDismissRequest = onFechar,

        title = {
            Text(
                text = "Os Termos e a política de Privacidade do WhatsApp serão atualizados.\n"
            )
        },

        text = {
            Text(
                text =
                    "texto",
            )
        },

        confirmButton = {

            Button(
                onClick = onFechar,

                shape = RoundedCornerShape(50.dp)
            ) {

                Text("Concordar")

            }

        }
    )
}

@Preview(showBackground = true)
@Composable
fun ModalTermosPreview() {
    ModalTermos(onFechar = { })
}