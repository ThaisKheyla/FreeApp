package com.example.freeapp.presentation.view.register.address

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.freeapp.R
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.components.FixedBlueButton
import com.example.freeapp.presentation.components.InputType
import com.example.freeapp.presentation.components.SelectionModal
import com.example.freeapp.presentation.components.TextField
import com.example.freeapp.presentation.navigation.Routes
import com.example.freeapp.presentation.viewmodel.AddressViewModel
import com.example.freeapp.presentation.viewmodel.previewRegistrationViewModel

@Composable
fun AddressScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AddressViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val user = uiState.user

    var showStates by remember {
        mutableStateOf(false)
    }

    var showCities by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.loadIbgeStates()
    }

    val isAddressValid =
        user.zipCode.isNotBlank() &&
                user.street.isNotBlank() &&
                user.number.isNotBlank() &&
                user.complement.isNotBlank() &&
                user.city.isNotBlank() &&
                user.state.isNotBlank()

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
                        R.string.address_title
                    ),
                    style = MaterialTheme.typography.headlineLarge
                )

                TextField(
                    value = user.zipCode,
                    label = stringResource(
                        R.string.address_zip_code
                    ),
                    onValueChange = viewModel::updateZipCode,
                    inputType = InputType.NUMBERS_ONLY
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TextField(
                        value = user.street,
                        label = stringResource(
                            R.string.address_street
                        ),
                        onValueChange = viewModel::updateAddress,
                        modifier = Modifier.weight(3f)
                    )

                    TextField(
                        value = user.number,
                        label = stringResource(
                            R.string.address_number
                        ),
                        onValueChange = viewModel::updateNumber,
                        inputType = InputType.NUMBERS_ONLY,
                        maxLength = 10,
                        modifier = Modifier.weight(1.4f)
                    )
                }

                TextField(
                    value = user.complement,
                    label = stringResource(
                        R.string.address_complement
                    ),
                    onValueChange = viewModel::updateComplement
                )

                TextField(
                    value = user.neighborhood,
                    label = stringResource(
                        R.string.address_neighborhood
                    ),
                    onValueChange = viewModel::updateNeighborhood,
                    inputType = InputType.LETTERS_ONLY
                )

                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = user.state,
                        label = if (
                            uiState.isIbgeLoading &&
                            uiState.states.isEmpty()
                        ) {
                            stringResource(
                                R.string.address_loading_states
                            )
                        } else {
                            stringResource(
                                R.string.address_state
                            )
                        },
                        onValueChange = { },
                        showSearch = true
                    )

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable {
                                if (uiState.states.isNotEmpty()) {
                                    showStates = true
                                }
                            }
                    )
                }

                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        value = user.city,
                        label = if (user.state.isBlank()) {
                            stringResource(
                                R.string.address_select_state_first
                            )
                        } else {
                            stringResource(
                                R.string.address_city
                            )
                        },
                        onValueChange = { },
                        showSearch = true
                    )

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable {
                                if (
                                    user.state.isNotBlank() &&
                                    uiState.cities.isNotEmpty()
                                ) {
                                    showCities = true
                                }
                            }
                    )
                }

                uiState.ibgeErrorMessage?.let { errorMessage ->
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            FixedBlueButton(
                text = stringResource(
                    R.string.common_continue
                ),
                enabled = isAddressValid,
                onClick = {
                    navController.navigate(
                        Routes.PROFESSIONAL_DATA
                    )
                }
            )

            if (showStates) {
                SelectionModal(
                    title = stringResource(
                        R.string.address_select_state
                    ),
                    items = uiState.states.map { state ->
                        state.name
                    },
                    onSelect = { state ->
                        viewModel.selectIbgeState(state)
                        showStates = false
                    }
                )
            }

            if (showCities) {
                SelectionModal(
                    title = stringResource(
                        R.string.address_select_city
                    ),
                    items = uiState.cities,
                    onSelect = { city ->
                        viewModel.updateCity(city)
                        showCities = false
                    }
                )
            }
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddressScreenPreview() {
    val registrationViewModel = previewRegistrationViewModel()
    AddressScreen(
        navController = androidx.navigation.compose.rememberNavController(),
        viewModel = AddressViewModel(registrationViewModel)
    )
}