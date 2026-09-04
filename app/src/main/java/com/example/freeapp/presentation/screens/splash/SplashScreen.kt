package com.example.freeapp.presentation.screens.splash


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.freeapp.R
import com.example.freeapp.presentation.navigation.Routes
import com.example.freeapp.presentation.theme.PrimaryBlue
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    LaunchedEffect(Unit) {
        delay(2500)

        navController.navigate(Routes.CAROUSEL) {
            popUpTo(Routes.SPLASH) {
                inclusive = true
            }
        }
    }

    SplashContent()
}

@Composable
private fun SplashContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PrimaryBlue),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.logo_free_white
            ),
            contentDescription = "Logo App Freelancer",
            modifier = Modifier.size(100.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SplashContent()
}