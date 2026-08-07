package com.projeto.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.freeapp.ui.theme.TextFieldBackground
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import com.example.freeapp.R
import com.example.freeapp.ui.theme.PrimaryBlue

@Composable
fun CampoTexto(
    valor: String,
    rotulo: String,
    onValorChange: (String) -> Unit,
    mostrarCheck: Boolean = false
) {

    OutlinedTextField(
        value = valor,
        onValueChange = onValorChange,

        placeholder = {
            Text(text = rotulo)
        },

        trailingIcon = {

            if (mostrarCheck) {

                Icon(
                    painter = painterResource(
                        id = R.drawable.fi_rr_check
                    ),
                    contentDescription = "Campo válido",
                    tint = PrimaryBlue
                )

            }

        },

        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),

        shape = RoundedCornerShape(10.dp),

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = TextFieldBackground,
            unfocusedContainerColor = TextFieldBackground
        )
    )
}