package com.projeto.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.freeapp.ui.theme.PrimaryBlue
@Composable
fun BotaoBlue(
    texto: String,
    onClick: () -> Unit
) {


//    modifier = Modifier.offset(
//        x = (-17).dp,
//        y = 8.dp

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),

        shape = RoundedCornerShape(50.dp),

        colors = ButtonDefaults.buttonColors(
            containerColor = PrimaryBlue
        )
    ) {

        Text(
            text = texto
        )

    }

}
