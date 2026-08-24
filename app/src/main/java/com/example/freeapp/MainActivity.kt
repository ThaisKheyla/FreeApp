package com.example.freeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.freeapp.ui.theme.FreeAppTheme
import com.projeto.ui.navigation.AppNavigation
import com.projeto.ui.screens.cadastro.DadosPessoaisScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            FreeAppTheme {
                AppNavigation()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    FreeAppTheme {
        AppNavigation()
    }
}