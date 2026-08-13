package com.projeto.ui.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.freeapp.ui.theme.PrimaryBlue
@Composable
fun BotaoBlue(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),

        shape = RoundedCornerShape(50.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryBlue,
            disabledContainerColor = PrimaryBlue.copy(alpha = 0.45f)
        )
    ) {

        Text(
            text = texto
        )

    }

}

@Composable
fun BoxScope.BotaoBlueFixo(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    BotaoBlue(
        texto = texto,
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .align(Alignment.BottomCenter)
            .padding(
                start = 24.dp,
                end = 24.dp,
                bottom = 24.dp
            )
            .navigationBarsPadding()
    )
}
