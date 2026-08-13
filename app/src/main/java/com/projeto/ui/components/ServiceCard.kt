package com.projeto.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.freeapp.ui.theme.fontColor

@Composable
fun ServiceCard(
    titulo: String,
    imagem: Int
) {

    Card(
        modifier = Modifier
            .width(115.dp)
            .height(100.dp),

        shape = RoundedCornerShape(18.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Image(
                painter = painterResource(imagem),
                contentDescription = titulo,
                modifier = Modifier.size(50.dp)
            )

            Text(
                text = titulo,
                color = fontColor
            )
        }
    }
}