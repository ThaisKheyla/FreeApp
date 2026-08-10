package com.projeto.ui.components

import android.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.freeapp.ui.theme.PrimaryBlue
import com.example.freeapp.ui.theme.PrimaryWhite

@Composable
fun BotaoWhite(
    texto: String,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        border = BorderStroke(
                    width = 2.dp,
                    color = PrimaryBlue
        ),
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .padding(horizontal = 8.dp)
            .height(54.dp),

        shape = RoundedCornerShape(50.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryWhite
        )

    ) {

        Text(
            color = PrimaryBlue,
            text = texto
        )

    }

}