package com.projeto.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projeto.ui.screens.LoginScreen
import com.projeto.ui.screens.cadastro.DadosBancariosScreen
import com.projeto.ui.screens.splash.SplashScreen
import com.projeto.ui.screens.welcome.WelcomeScreen
import com.projeto.ui.screens.cadastro.DadosPessoaisScreen
import com.projeto.ui.screens.cadastro.DadosProfissaoScreen
import com.projeto.ui.screens.cadastro.EnderecosScreen
import com.projeto.ui.screens.carousel.CarouselScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }

        composable(Routes.WELCOME) {
            WelcomeScreen(navController)
        }

        composable(Routes.CAROUSEL) {
            CarouselScreen(navController)
        }

        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        composable(Routes.DADOS_PESSOAIS) {
            DadosPessoaisScreen(navController)
        }

        composable(Routes.ENDERECOS) {
            EnderecosScreen(navController)
        }

        composable(Routes.DADOS_PROFISSAO) {
            DadosProfissaoScreen(navController)
        }
        composable(Routes.DADOS_BANCARIOS) {
            DadosBancariosScreen(navController)
        }
    }
}
