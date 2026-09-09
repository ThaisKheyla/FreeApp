package com.example.freeapp.presentation.screens.register

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
                text = "Endereço Pessoal",
                style = MaterialTheme.typography.headlineLarge
            )
            TextField(
                value = usuario.zipCode,
                label = "CEP",
                onValueChange = viewModel::updateZipCode,
                inputType = InputType.NUMBERS_ONLY
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                TextField(
                    value = usuario.street,
                    label = "Endereço",
                    onValueChange = viewModel::updateAddress,
                    modifier = Modifier.weight(3f)
                )

                TextField(
                    value = usuario.number,
                    label = "Nº",
                    onValueChange = viewModel::updateNumber,
                    inputType = InputType.NUMBERS_ONLY,
                    maxLength = 10,
                    modifier = Modifier.weight(1.4f)
                )
            }


            TextField(
                value = usuario.complement,
                label = "Complemento",
                    onValueChange = viewModel::updateComplement
            )

            TextField(
                value = usuario.neighborhood,
                label = "Bairro",
                onValueChange = viewModel::updateNeighborhood,
                inputType = InputType.LETTERS_ONLY
            )

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = usuario.state,
                    label = if (viewModel.ibgeLoading && viewModel.ibgeStates.isEmpty()) "Carregando estados..." else "Estado",
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
                    label = if (usuario.state.isBlank()) "Selecione o estado primeiro" else "Cidade",
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
                text = "CONTINUAR",
                enabled = enderecoValido,
                onClick = {
                    navController.navigate(Routes.PROFESSIONAL_DATA)
                }
            )

            if (mostrarEstados) {
                SelectionModal(
                    title = "Selecione o estado",
                    items = viewModel.ibgeStates.map { estado -> estado.nome },
                    onSelect = { estado ->
                        viewModel.selectIbgeState(estado)
                        mostrarEstados = false
                    }
                )
            }

            if (mostrarCidades) {
                SelectionModal(
                    title = "Selecione a cidade",
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddressScreenPreview() {
    AddressScreen(navController = rememberNavController())
}