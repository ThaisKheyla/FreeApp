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
import com.example.freeapp.presentation.screens.pagamento.PagamentoScreen
import com.example.freeapp.presentation.screens.forgotPassword.EsqueciSenhaScreen
import com.example.freeapp.presentation.viewmodel.UsuarioViewModel
import com.example.freeapp.presentation.screens.carousel.CarouselScreen
import com.example.freeapp.presentation.screens.home.HomeScreen
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val usuarioViewModel = remember { _root_ide_package_.com.example.freeapp.ui.viewmodel.UsuarioViewModel() }

    NavHost(
        navController = navController,
        startDestination = _root_ide_package_.com.example.freeapp.presentation.navigation.Routes.SPLASH
    ) {

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.SPLASH) {
            _root_ide_package_.com.example.freeapp.ui.screens.splash.SplashScreen(navController)
        }

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.WELCOME) {
            _root_ide_package_.com.example.freeapp.ui.screens.welcome.WelcomeScreen(navController)
        }

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.CAROUSEL) {
            _root_ide_package_.com.example.freeapp.ui.screens.carousel.CarouselScreen(navController)
        }

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.LOGIN) {
            _root_ide_package_.com.projeto.ui.screens.LoginScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.DADOS_PESSOAIS) {
            _root_ide_package_.com.example.freeapp.ui.screens.cadastro.DadosPessoaisScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.ENDERECOS) {
            _root_ide_package_.com.example.freeapp.ui.screens.cadastro.EnderecosScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.DADOS_PROFISSAO) {
            _root_ide_package_.com.example.freeapp.ui.screens.cadastro.DadosProfissaoScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }
        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.DADOS_BANCARIOS) {
            _root_ide_package_.com.example.freeapp.ui.screens.cadastro.DadosBancariosScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }
        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.FORMA_PAGAMENTO) {
            _root_ide_package_.com.example.freeapp.ui.screens.pagamento.PagamentoScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }
        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.CRIAR_SENHA) {
            _root_ide_package_.com.example.freeapp.ui.screens.cadastro.CriarSenhaScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }
        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.ESQUECI_SENHA) {
            _root_ide_package_.com.example.freeapp.ui.screens.senha.EsqueciSenhaScreen(
                navController,
                viewModel = usuarioViewModel
            )
        }
        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.HOME) {
            _root_ide_package_.com.example.freeapp.ui.screens.home.HomeScreen(viewModel = usuarioViewModel)
        }
    }
}
