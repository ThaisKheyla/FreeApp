package com.projeto.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projeto.ui.screens.splash.SplashScreen
import com.projeto.ui.screens.welcome.WelcomeScreen
import com.projeto.ui.screens.cadastro.DadosPessoaisScreen
import com.projeto.ui.screens.cadastro.EnderecosScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        //descommentar depois...
        //startDestination = Routes.SPLASH
        startDestination = Routes.DADOS_PESSOAIS
    ) {

        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }

        composable(Routes.WELCOME) {
            WelcomeScreen()
        }

        composable(Routes.DADOS_PESSOAIS) {
            DadosPessoaisScreen(navController)
        }

        composable(Routes.ENDERECOS) {
            EnderecosScreen()
        }
    }
}

