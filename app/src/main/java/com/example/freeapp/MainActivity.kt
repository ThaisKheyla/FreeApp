package com.example.freeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.projeto.ui.navigation.AppNavigation
import com.example.freeapp.ui.theme.FreeAppTheme

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