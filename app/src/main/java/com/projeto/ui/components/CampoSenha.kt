package com.projeto.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freeapp.ui.theme.PrimaryBlue
import com.example.freeapp.ui.theme.TextFieldBackground

@Composable
fun CampoSenha(
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

@Preview(showBackground = true)
@Composable
fun CampoSenhaPreview() {
    CampoSenha(
        valor = "",
        rotulo = "Senha",
        onValorChange = { }
    )
}