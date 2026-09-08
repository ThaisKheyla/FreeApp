package com.example.freeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.presentation.screens.cadastro.DadosBancariosScreen
import com.example.freeapp.presentation.screens.splash.SplashScreen
import com.example.freeapp.presentation.screens.welcome.WelcomeScreen
import com.example.freeapp.presentation.screens.cadastro.DadosPessoaisScreen
import com.example.freeapp.presentation.screens.cadastro.DadosProfissaoScreen
import com.example.freeapp.presentation.screens.cadastro.EnderecosScreen
import com.example.freeapp.presentation.screens.cadastro.CriarSenhaScreen
import com.example.freeapp.presentation.viewmodel.UsuarioViewModel
import com.example.freeapp.presentation.screens.carousel.CarouselScreen
import com.example.freeapp.presentation.screens.forgotPassword.ForgotPasswordScreen
import com.example.freeapp.presentation.screens.home.HomeScreen
import com.example.freeapp.presentation.screens.payment.PaymentScreen
import com.projeto.ui.screens.LoginScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val usuarioViewModel = remember { UsuarioViewModel() }

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
            LoginScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }

        composable(Routes.DADOS_PESSOAIS) {
            DadosPessoaisScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }

        composable(Routes.ENDERECOS) {
            EnderecosScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }

        composable(Routes.DADOS_PROFISSAO) {
            DadosProfissaoScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }
        composable(Routes.DADOS_BANCARIOS) {
            DadosBancariosScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }
        composable(Routes.FORMA_PAGAMENTO) {
            PaymentScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }
        composable(Routes.CRIAR_SENHA) {
            CriarSenhaScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }
        composable(Routes.ESQUECI_SENHA) {
            ForgotPasswordScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }
        composable(Routes.HOME) {
            HomeScreen(viewModel = usuarioViewModel)
        }
    }
}
