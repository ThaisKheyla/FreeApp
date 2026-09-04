package com.example.freeapp.presentation.screens.carousel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.presentation.theme.PrimaryBlue
import com.example.freeapp.presentation.theme.neutreColor
import com.example.freeapp.presentation.components.BlueButton
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.navigation.Routes


@Composable
fun CarouselScreen(
    navController: NavController
) {
    var currentPage by remember {
        mutableStateOf(0)
    }

    val page = carouselPages[currentPage]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp
            ),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            BackButton(
                onClick = {

                    if (currentPage > 0) {
                        currentPage--
                    }

                }
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Image(
            painter = painterResource(
                id = page.image
            ),
            contentDescription = null,

            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp),

            contentScale = ContentScale.Fit
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = page.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = page.description,
            color = neutreColor,
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(36.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            repeat(carouselPages.size) { index ->

                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(
                            color =
                                if (index == currentPage)
                                    PrimaryBlue
                                else
                                    Color(0xFFD9D9D9),

                            shape = CircleShape
                        )
                )
            }
        }

        Spacer(
            modifier = Modifier.height(40.dp)
        )

        BlueButton(
            text = "Próximo",

            onClick = {
                if (currentPage < carouselPages.lastIndex) {

                    currentPage++

                } else {

                    navController.navigate(Routes.WELCOME) {

                        popUpTo(Routes.CAROUSEL) {
                            inclusive = true
                        }
                    }
                }
            }
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Pular",
            color = neutreColor,
            fontWeight = FontWeight.Medium,

            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clickable {

                    navController.navigate(Routes.WELCOME) {

                        popUpTo(Routes.CAROUSEL) {
                            inclusive = true
                        }
                    }
                },

            textAlign = TextAlign.End
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewCarouselScreen() {

    val navController = rememberNavController()

    CarouselScreen(
        navController = navController
    )
}