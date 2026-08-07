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
    modifier: Modifier = Modifier.fillMaxWidth(),
    mostrarCheck: Boolean = false,
    mostrarSearch: Boolean = false
)

{

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

        modifier = modifier
            .height(55.dp),

        shape = RoundedCornerShape(10.dp),

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = TextFieldBackground,
            unfocusedContainerColor = TextFieldBackground
        )
    )
}