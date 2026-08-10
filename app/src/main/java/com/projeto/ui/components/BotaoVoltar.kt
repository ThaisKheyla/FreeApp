package com.projeto.ui.components

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.freeapp.ui.theme.PrimaryBlue
import androidx.compose.ui.res.painterResource
import com.example.freeapp.R

@Composable
fun BotaoVoltar(
    onClick: () -> Unit,
    tint: Color = PrimaryBlue
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.offset(
            x = (-17).dp,
            y = 8.dp
        )
    )
    {
        Icon(
            painter = painterResource(
                id = R.drawable.fi_rr_arrow_left
            ),
            contentDescription = "Voltar",
            tint = tint,
            modifier = Modifier.size(20.dp)
        )
    }
}