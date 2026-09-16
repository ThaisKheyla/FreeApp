package com.example.freeapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.data.remote.ClienteIbge
import com.example.freeapp.data.repository.RepositorioFirebase
import com.example.freeapp.data.repository.IbgeRepository
import com.example.freeapp.domain.usecase.auth.GetCurrentUserNameUseCase
import com.example.freeapp.domain.usecase.auth.LoginUseCase
import com.example.freeapp.domain.usecase.auth.RegisterUserUseCase
import com.example.freeapp.domain.usecase.auth.ResetPasswordUseCase
import com.example.freeapp.domain.usecase.location.LoadCitiesUseCase
import com.example.freeapp.domain.usecase.location.LoadStatesUseCase
import com.example.freeapp.presentation.view.register.bank.BankDataScreen
import com.example.freeapp.presentation.view.splash.SplashScreen
import com.example.freeapp.presentation.view.welcome.WelcomeScreen
import com.example.freeapp.presentation.view.register.personal.PersonalDataScreen
import com.example.freeapp.presentation.view.register.professional.ProfessionalDataScreen
import com.example.freeapp.presentation.view.register.address.AddressScreen
import com.example.freeapp.presentation.view.register.password.CreatePasswordScreen
import com.example.freeapp.presentation.view.payment.PaymentScreen
import com.example.freeapp.presentation.view.forgotPassword.ForgotPasswordScreen
import com.example.freeapp.presentation.viewmodel.AuthViewModel
import com.example.freeapp.presentation.view.carousel.CarouselScreen
import com.example.freeapp.presentation.view.home.HomeScreen
import com.example.freeapp.presentation.view.login.LoginScreen
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
    val authRepository = remember { RepositorioFirebase() }
    val locationRepository = remember { IbgeRepository(ClienteIbge.servicoIbge) }
    val loginUseCase = remember { LoginUseCase(authRepository) }
    val getCurrentUserNameUseCase = remember { GetCurrentUserNameUseCase(authRepository) }
    val resetPasswordUseCase = remember { ResetPasswordUseCase(authRepository) }
    val registerUserUseCase = remember { RegisterUserUseCase(authRepository) }
    val loadStatesUseCase = remember { LoadStatesUseCase(locationRepository) }
    val loadCitiesUseCase = remember { LoadCitiesUseCase(locationRepository) }
    val authViewModel = remember {
        AuthViewModel(
            loginUseCase = loginUseCase,
            getCurrentUserNameUseCase = getCurrentUserNameUseCase,
            resetPasswordUseCase = resetPasswordUseCase
        )
    }
    val registrationViewModel = remember {
        RegistrationViewModel(
            registerUserUseCase = registerUserUseCase,
            loadStatesUseCase = loadStatesUseCase,
            loadCitiesUseCase = loadCitiesUseCase
        )
    }
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
