package com.example.freeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.presentation.screens.cadastro.BankDataScreen
import com.example.freeapp.presentation.screens.splash.SplashScreen
import com.example.freeapp.presentation.screens.welcome.WelcomeScreen
import com.example.freeapp.presentation.screens.cadastro.PersonalDataScreen
import com.example.freeapp.presentation.screens.cadastro.ProfessionalDataScreen
import com.example.freeapp.presentation.screens.cadastro.AddressScreen
import com.example.freeapp.presentation.screens.cadastro.CreatePasswordScreen
import com.example.freeapp.presentation.screens.payment.PaymentScreen
import com.example.freeapp.presentation.screens.forgotPassword.ForgotPasswordScreen
import com.example.freeapp.presentation.viewmodel.AuthViewModel
import com.example.freeapp.presentation.screens.carousel.CarouselScreen
import com.example.freeapp.presentation.screens.home.HomeScreen
import com.example.freeapp.presentation.viewmodel.AddressViewModel
import com.example.freeapp.presentation.viewmodel.BankDataViewModel
import com.example.freeapp.presentation.viewmodel.CreatePasswordViewModel
import com.example.freeapp.presentation.viewmodel.PersonalDataViewModel
import com.example.freeapp.presentation.viewmodel.PaymentViewModel
import com.example.freeapp.presentation.viewmodel.ProfessionalDataViewModel
import com.example.freeapp.presentation.viewmodel.RegistrationViewModel
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel = remember { AuthViewModel() }
    val registrationViewModel = remember { RegistrationViewModel() }
    val personalDataViewModel = remember { PersonalDataViewModel(registrationViewModel) }
    val addressViewModel = remember { AddressViewModel(registrationViewModel) }
    val professionalDataViewModel = remember { ProfessionalDataViewModel(registrationViewModel) }
    val bankDataViewModel = remember { BankDataViewModel(registrationViewModel) }
    val paymentViewModel = remember { PaymentViewModel(registrationViewModel) }
    val createPasswordViewModel = remember { CreatePasswordViewModel(registrationViewModel) }

    NavHost(
        navController = navController,
        startDestination = _root_ide_package_.com.example.freeapp.presentation.navigation.Routes.SPLASH
    ) {

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.SPLASH) {
            _root_ide_package_.com.example.freeapp.presentation.screens.splash.SplashScreen(navController)
        }

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.WELCOME) {
            _root_ide_package_.com.example.freeapp.presentation.screens.welcome.WelcomeScreen(navController)
        }

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.CAROUSEL) {
            _root_ide_package_.com.example.freeapp.presentation.screens.carousel.CarouselScreen(navController)
        }

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.LOGIN) {
            _root_ide_package_.com.example.freeapp.presentation.screens.login.LoginScreen(
                navController,
                viewModel = authViewModel
            )
        }

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.DADOS_PESSOAIS) {
            _root_ide_package_.com.example.freeapp.presentation.screens.cadastro.PersonalDataScreen(
                navController,
                viewModel = personalDataViewModel
            )
        }

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.ENDERECOS) {
            _root_ide_package_.com.example.freeapp.presentation.screens.cadastro.AddressScreen(
                navController,
                viewModel = addressViewModel
            )
        }

        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.DADOS_PROFISSAO) {
            _root_ide_package_.com.example.freeapp.presentation.screens.cadastro.ProfessionalDataScreen(
                navController,
                viewModel = professionalDataViewModel
            )
        }
        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.DADOS_BANCARIOS) {
            _root_ide_package_.com.example.freeapp.presentation.screens.cadastro.BankDataScreen(
                navController,
                viewModel = bankDataViewModel
            )
        }
        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.FORMA_PAGAMENTO) {
            _root_ide_package_.com.example.freeapp.presentation.screens.payment.PaymentScreen(
                navController,
                viewModel = paymentViewModel
            )
        }
        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.CRIAR_SENHA) {
            _root_ide_package_.com.example.freeapp.presentation.screens.cadastro.CreatePasswordScreen(
                navController,
                viewModel = createPasswordViewModel
            )
        }
        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.ESQUECI_SENHA) {
            _root_ide_package_.com.example.freeapp.presentation.screens.forgotPassword.ForgotPasswordScreen(
                navController,
                viewModel = authViewModel
            )
        }
        composable(_root_ide_package_.com.example.freeapp.presentation.navigation.Routes.HOME) {
            _root_ide_package_.com.example.freeapp.presentation.screens.home.HomeScreen(viewModel = authViewModel)
        }
    }
}
