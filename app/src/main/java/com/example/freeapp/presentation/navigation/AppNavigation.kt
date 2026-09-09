package com.example.freeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.presentation.screens.register.BankDataScreen
import com.example.freeapp.presentation.screens.splash.SplashScreen
import com.example.freeapp.presentation.screens.welcome.WelcomeScreen
import com.example.freeapp.presentation.screens.register.PersonalDataScreen
import com.example.freeapp.presentation.screens.register.ProfessionalDataScreen
import com.example.freeapp.presentation.screens.register.AddressScreen
import com.example.freeapp.presentation.screens.register.CreatePasswordScreen
import com.example.freeapp.presentation.screens.payment.PaymentScreen
import com.example.freeapp.presentation.screens.forgotPassword.ForgotPasswordScreen
import com.example.freeapp.presentation.viewmodel.AuthViewModel
import com.example.freeapp.presentation.screens.carousel.CarouselScreen
import com.example.freeapp.presentation.screens.home.HomeScreen
import com.example.freeapp.presentation.screens.login.LoginScreen
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
                viewModel = authViewModel
            )
        }

        composable(Routes.PERSONAL_DATA) {
            PersonalDataScreen(
                navController,
                viewModel = personalDataViewModel
            )
        }

        composable(Routes.ADDRESS) {
           AddressScreen(
                navController,
                viewModel = addressViewModel
            )
        }

        composable(Routes.PROFESSIONAL_DATA) {
            ProfessionalDataScreen(
                navController,
                viewModel = professionalDataViewModel
            )
        }
        composable(Routes.BANK_DETAILS) {
            BankDataScreen(
                navController,
                viewModel = bankDataViewModel
            )
        }
        composable(Routes.PAYMENT) {
          PaymentScreen(
                navController,
                viewModel = paymentViewModel
            )
        }
        composable(Routes.CREATE_PASSWORD) {
            CreatePasswordScreen(
                navController,
                viewModel = createPasswordViewModel
            )
        }
        composable(Routes.FORGOT_PASSWORD) {
           ForgotPasswordScreen(
                navController,
                viewModel = authViewModel
            )
        }
        composable(Routes.HOME) {
            HomeScreen(viewModel = authViewModel)
        }
    }
}
