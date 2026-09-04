package com.example.freeapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freeapp.presentation.theme.PrimaryBlue

@Composable
fun AccountTypeOption(
    texto: String,
    selecionado: Boolean,
    onSelecionar: () -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onSelecionar()
        }
    ) {

        RadioButton(
            selected = selecionado,
            onClick = onSelecionar,
            colors = RadioButtonDefaults.colors(
                selectedColor = PrimaryBlue
            )
        )

        Spacer(
            modifier = Modifier.width(4.dp)
        )

        Text(
            text = texto,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

