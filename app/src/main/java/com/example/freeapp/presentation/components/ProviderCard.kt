package com.example.freeapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freeapp.R
import com.example.freeapp.presentation.theme.PrimaryBlue
import com.example.freeapp.presentation.theme.PrimaryWhite
import com.example.freeapp.presentation.theme.fontColor

@Composable
fun ProviderCard(
    name: String,
    profession: String,
    rating: String,
    image: Int,
    imageBackgroundColor: Color
) {

    Card(
        modifier = Modifier
            .width(160.dp)
            .height(200.dp),

        shape = RoundedCornerShape(18.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),

        colors = CardDefaults.cardColors(
            containerColor = PrimaryWhite
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(105.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(imageBackgroundColor),

                contentAlignment = Alignment.BottomCenter
            ) {

                Image(
                    painter = painterResource(id = image),
                    contentDescription = name,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(95.dp),

                    contentScale = ContentScale.Fit
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = name,
                color = fontColor,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = profession,
                color = Color(0xFF8696BB),
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "★",
                    color = PrimaryBlue,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = rating,
                    color = PrimaryBlue,
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "Details",
                    color = PrimaryWhite,
                    style = MaterialTheme.typography.bodySmall,

                    modifier = Modifier
                        .background(
                            color = PrimaryBlue,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(
                            horizontal = 10.dp,
                            vertical = 4.dp
                        )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProviderCardPreview() {
    ProviderCard(
        name = "Ana Silva",
        profession = "Eletricista",
        rating = "4.8",
        image = R.drawable.img_provider1,
        imageBackgroundColor = Color(0xFFD4E5F8)
    )
}