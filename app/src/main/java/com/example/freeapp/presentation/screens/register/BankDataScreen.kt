package com.example.freeapp.presentation.screens.register

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.freeapp.presentation.components.AccountTypeOption
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.components.FixedBlueButton
import com.example.freeapp.presentation.components.InputType
import com.example.freeapp.presentation.components.TextField
import com.example.freeapp.presentation.viewmodel.BankDataViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.presentation.navigation.Routes

@Composable
fun BankDataScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: BankDataViewModel = BankDataViewModel()
) {
    val usuario = viewModel.user
    var tipoContaSelecionado by remember {
        mutableStateOf("Pessoa Física")
    }
    val dadosBancariosValidos =
        usuario.agency.isNotBlank() &&
            usuario.account.isNotBlank() &&
            usuario.accountType.isNotBlank() &&
                usuario.pix.isNotBlank()

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp)
                    .padding(bottom = 96.dp),
                verticalArrangement = Arrangement.spacedBy(22.dp)
            ) {
            BackButton(
                onClick = {
                    navController.popBackStack()
                }
            )
            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Dados Bancários Para Recebimento",
                style = MaterialTheme.typography.headlineLarge
            )
            TextField(
                value = usuario.agency,
                label = "Agência",
                onValueChange = viewModel::updateAgency,
                inputType = InputType.NUMBERS_ONLY

            )

            TextField(
                value = usuario.account,
                label = "Conta",
                onValueChange = viewModel::updateAccount,
                inputType = InputType.NUMBERS_ONLY
            )

            TextField(
                value = usuario.accountType,
                label = "Qual é o seu tipo de conta",
                onValueChange = viewModel::updateAccountType
            )

            AccountTypeOption(
                text = "Pessoa Física",
                selected = tipoContaSelecionado == "Pessoa Física",
                onSelect = {
                    tipoContaSelecionado = "Pessoa Física"
                    viewModel.updateAccountType("Pessoa Física")
                }
            )

            AccountTypeOption(
                text = "Pessoa Jurídica",
                selected = tipoContaSelecionado == "Pessoa Jurídica",
                onSelect = {
                    tipoContaSelecionado = "Pessoa Jurídica"
                    viewModel.updateAccountType("Pessoa Jurídica")
                }
            )
            TextField(
                value = usuario.pix,
                label = "PIX",
                onValueChange = viewModel::updatePix
            )
            }

            FixedBlueButton(
                text = "CONTINUAR",
                enabled = dadosBancariosValidos,
                onClick = {
                    navController.navigate(Routes.PAYMENT)
                }
            )

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BankDataScreenPreview() {
    BankDataScreen(navController = rememberNavController())
}