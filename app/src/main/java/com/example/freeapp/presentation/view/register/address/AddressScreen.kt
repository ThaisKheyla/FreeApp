package com.example.freeapp.presentation.view.register.address

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.freeapp.presentation.viewmodel.AddressViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.navigation.Routes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.freeapp.R
import com.example.freeapp.presentation.components.FixedBlueButton
import com.example.freeapp.presentation.components.SelectionModal
import com.example.freeapp.presentation.components.InputType
import com.example.freeapp.presentation.components.TextField

@Composable
fun AddressScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: AddressViewModel = AddressViewModel()
){
    val usuario = viewModel.user
    var mostrarEstados by remember { mutableStateOf(false) }
    var mostrarCidades by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadIbgeStates()
    }

    val enderecoValido =
        usuario.zipCode.isNotBlank() &&
            usuario.street.isNotBlank() &&
                usuario.number.isNotBlank() &&
                usuario.complement.isNotBlank() &&
                usuario.city.isNotBlank() &&
                usuario.state.isNotBlank()

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
                text = stringResource(
                    R.string.address_title
                ),
                style = MaterialTheme.typography.headlineLarge
            )
            TextField(
                value = usuario.zipCode,
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
                    value = usuario.street,
                    label = stringResource(
                        R.string.address_street
                    ),
                    onValueChange = viewModel::updateAddress,
                    modifier = Modifier.weight(3f)
                )

                TextField(
                    value = usuario.number,
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
                value = usuario.complement,
                label = stringResource(
                    R.string.address_complement
                ),
                    onValueChange = viewModel::updateComplement
            )

            TextField(
                value = usuario.neighborhood,
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
                    value = usuario.state,
                    label =
                        if (viewModel.ibgeLoading && viewModel.ibgeStates.isEmpty())
                            stringResource(
                                R.string.address_loading_states
                            )
                        else
                            stringResource(
                                R.string.address_state
                            ),
                    onValueChange = { },
                    showSearch = true
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable {
                            if (viewModel.ibgeStates.isNotEmpty()) {
                                mostrarEstados = true
                            }
                        }
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = usuario.city,
                    label =
                        if (usuario.state.isBlank())
                            stringResource(
                                R.string.address_select_state_first
                            )
                        else
                            stringResource(
                                R.string.address_city
                            ),
                    onValueChange = { },
                    showSearch = true
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable {
                            if (usuario.state.isNotBlank() && viewModel.ibgeCities.isNotEmpty()) {
                                mostrarCidades = true
                            }
                        }
                )
            }

            viewModel.ibgeErrorMessage?.let { mensagem ->
                Text(
                    text = mensagem,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }
            }

            FixedBlueButton(
                text = stringResource(
                    R.string.common_continue
                ),
                enabled = enderecoValido,
                onClick = {
                    navController.navigate(Routes.PROFESSIONAL_DATA)
                }
            )

            if (mostrarEstados) {
                SelectionModal(
                    title = stringResource(
                        R.string.address_select_state
                    ),
                    items = viewModel.ibgeStates.map { estado -> estado.nome },
                    onSelect = { estado ->
                        viewModel.selectIbgeState(estado)
                        mostrarEstados = false
                    }
                )
            }

            if (mostrarCidades) {
                SelectionModal(
                    title = stringResource(
                        R.string.address_select_city
                    ),
                    items = viewModel.ibgeCities,
                    onSelect = { cidade ->
                        viewModel.updateCity(cidade)
                        mostrarCidades = false
                    }
                )
            }

        }
    }
}

