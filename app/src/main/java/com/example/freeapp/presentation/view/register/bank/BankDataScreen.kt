package com.example.freeapp.presentation.view.register.bank

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.freeapp.R
import com.example.freeapp.presentation.components.AccountTypeOption
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.components.FixedBlueButton
import com.example.freeapp.presentation.components.InputType
import com.example.freeapp.presentation.components.TextField
import com.example.freeapp.presentation.navigation.Routes
import com.example.freeapp.presentation.viewmodel.BankDataViewModel

@Composable
fun BankDataScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: BankDataViewModel = BankDataViewModel()
) {
    val user = viewModel.user

    val individualAccount = stringResource(
        R.string.bank_data_individual_account
    )

    val businessAccount = stringResource(
        R.string.bank_data_business_account
    )

    var selectedAccountType by remember(individualAccount) {
        mutableStateOf(
            user.accountType.ifBlank {
                individualAccount
            }
        )
    }

    val isBankDataValid =
        user.agency.isNotBlank() &&
                user.account.isNotBlank() &&
                user.accountType.isNotBlank() &&
                user.pix.isNotBlank()

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
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
                    text = stringResource(
                        R.string.bank_data_title
                    ),
                    style = MaterialTheme.typography.headlineLarge
                )

                TextField(
                    value = user.agency,
                    label = stringResource(
                        R.string.bank_data_agency
                    ),
                    onValueChange = viewModel::updateAgency,
                    inputType = InputType.NUMBERS_ONLY
                )

                TextField(
                    value = user.account,
                    label = stringResource(
                        R.string.bank_data_account
                    ),
                    onValueChange = viewModel::updateAccount,
                    inputType = InputType.NUMBERS_ONLY
                )

                TextField(
                    value = user.accountType,
                    label = stringResource(
                        R.string.bank_data_account_type
                    ),
                    onValueChange = viewModel::updateAccountType
                )

                AccountTypeOption(
                    text = individualAccount,
                    selected = selectedAccountType == individualAccount,
                    onSelect = {
                        selectedAccountType = individualAccount
                        viewModel.updateAccountType(
                            individualAccount
                        )
                    }
                )

                AccountTypeOption(
                    text = businessAccount,
                    selected = selectedAccountType == businessAccount,
                    onSelect = {
                        selectedAccountType = businessAccount
                        viewModel.updateAccountType(
                            businessAccount
                        )
                    }
                )

                TextField(
                    value = user.pix,
                    label = stringResource(
                        R.string.bank_data_pix
                    ),
                    onValueChange = viewModel::updatePix
                )
            }

            FixedBlueButton(
                text = stringResource(
                    R.string.common_continue
                ),
                enabled = isBankDataValid,
                onClick = {
                    navController.navigate(
                        Routes.PAYMENT
                    )
                }
            )
        }
    }
}