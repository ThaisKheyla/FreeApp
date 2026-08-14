package com.projeto.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.projeto.ui.screens.cadastro.CriarSenhaScreen
import com.projeto.ui.screens.pagamento.PagamentoScreen
import com.projeto.ui.screens.senha.EsqueciSenhaScreen
import com.projeto.ui.viewmodel.UsuarioViewModel
import com.projeto.ui.screens.carousel.CarouselScreen
import com.projeto.ui.screens.home.HomeScreen
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
            LoginScreen(navController, viewModel = usuarioViewModel)
        }

        composable(Routes.DADOS_PESSOAIS) {
            DadosPessoaisScreen(navController, viewModel = usuarioViewModel)
        }

        composable(Routes.ENDERECOS) {
            EnderecosScreen(navController, viewModel = usuarioViewModel)
        }

        composable(Routes.DADOS_PROFISSAO) {
            DadosProfissaoScreen(navController, viewModel = usuarioViewModel)
        }
        composable(Routes.DADOS_BANCARIOS) {
            DadosBancariosScreen(navController, viewModel = usuarioViewModel)
        }
        composable(Routes.FORMA_PAGAMENTO) {
            PagamentoScreen(navController, viewModel = usuarioViewModel)
        }
        composable(Routes.CRIAR_SENHA) {
            CriarSenhaScreen(navController, viewModel = usuarioViewModel)
        }
        composable(Routes.ESQUECI_SENHA) {
            EsqueciSenhaScreen(navController, viewModel = usuarioViewModel)
        }
        composable(Routes.HOME) {
            HomeScreen()
        }
    }
}
