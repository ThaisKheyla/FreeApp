package com.projeto.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.freeapp.ui.theme.TextFieldBackground
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.freeapp.R
import com.example.freeapp.ui.theme.PrimaryBlue

enum class TipoEntrada {
    LIVRE,
    APENAS_LETRAS,
    APENAS_NUMEROS,
    NUMEROS_E_BARRA
}

@Composable
fun CampoTexto(
    valor: String,
    rotulo: String,
    onValorChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    mostrarCheck: Boolean = false,
    mostrarSearch: Boolean = false,
    isError: Boolean = false,
    tipoEntrada: TipoEntrada = TipoEntrada.LIVRE,
    maxLength: Int? = null
) {

    OutlinedTextField(
        value = valor,
        onValueChange = { novoValor ->
            val valorFiltrado = novoValor.filter { caractere ->
                when (tipoEntrada) {
                    TipoEntrada.LIVRE -> true
                    TipoEntrada.APENAS_LETRAS -> caractere.isLetter() || caractere == ' '
                    TipoEntrada.APENAS_NUMEROS -> caractere.isDigit()
                    TipoEntrada.NUMEROS_E_BARRA -> caractere.isDigit() || caractere == '/'
                }
            }

            onValorChange(
                if (maxLength != null) valorFiltrado.take(maxLength) else valorFiltrado
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = when (tipoEntrada) {
                TipoEntrada.APENAS_NUMEROS,
                TipoEntrada.NUMEROS_E_BARRA -> KeyboardType.Number
                TipoEntrada.APENAS_LETRAS -> KeyboardType.Text
                TipoEntrada.LIVRE -> KeyboardType.Text
            }
        ),

        placeholder = {
            Text(
                text = rotulo,
                style = MaterialTheme.typography.bodySmall
            )
        },

        trailingIcon = {
            //icone de check
            if (mostrarCheck) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.fi_rr_check
                    ),
                    contentDescription = "Campo válido",
                    tint = PrimaryBlue,
                    modifier = Modifier.size(18.dp)
                )
            }

            if (mostrarSearch) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.fi_rr_search
                    ),
                    contentDescription = "Pesquisar",
                    tint = PrimaryBlue,
                    modifier = Modifier.size(18.dp)
                )
            }
        },

        isError = isError,

        modifier = modifier
            .height(55.dp),

        shape = RoundedCornerShape(10.dp),

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor =
                if (isError) Color.Red else Color.Transparent,

            unfocusedBorderColor =
                if (isError) Color.Red else Color.Transparent,

            focusedContainerColor = TextFieldBackground,
            unfocusedContainerColor = TextFieldBackground
        )
    )
}

@Preview(showBackground = true)
@Composable
fun CampoTextoPreview() {
    CampoTexto(
        valor = "",
        rotulo = "Nome completo",
        onValorChange = { },
        mostrarCheck = true
    )
}