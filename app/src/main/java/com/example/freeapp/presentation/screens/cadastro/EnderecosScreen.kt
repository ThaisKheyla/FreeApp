package com.example.freeapp.presentation.screens.cadastro

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
import com.example.freeapp.presentation.viewmodel.UsuarioViewModel
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
import com.example.freeapp.presentation.components.InputType
import com.example.freeapp.presentation.components.SelectionModal
import com.example.freeapp.presentation.components.TextField

@Composable
fun EnderecosScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
        viewModel: com.example.freeapp.presentation.viewmodel.UsuarioViewModel = UsuarioViewModel()
){
    val usuario = viewModel.usuario
    var mostrarEstados by remember { mutableStateOf(false) }
    var mostrarCidades by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadIbgeStates()
    }

    val enderecoValido =
        usuario.cep.isNotBlank() &&
                usuario.endereco.isNotBlank() &&
                usuario.numero.isNotBlank() &&
                usuario.complemento.isNotBlank() &&
                usuario.bairro.isNotBlank() &&
                usuario.cidade.isNotBlank() &&
                usuario.estado.isNotBlank()

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
                value = usuario.cep,
                label = "CEP",
                onValueChange = viewModel::updateZipCode,
                inputType = InputType.NUMBERS_ONLY
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                TextField(
                    value = usuario.endereco,
                    label = "Endereço",
                    onValueChange = viewModel::updateAddress,
                    modifier = Modifier.weight(3f)
                )

                TextField(
                    value = usuario.numero,
                    label = "Nº",
                    onValueChange = viewModel::updateNumber,
                    inputType = InputType.NUMBERS_ONLY,
                    maxLength = 10,
                    modifier = Modifier.weight(1.4f)
                )
            }


            TextField(
                value = usuario.complemento,
                label = "Complemento",
                    onValueChange = viewModel::updateComplement
            )

            TextField(
                value = usuario.bairro,
                label = "Bairro",
                onValueChange = viewModel::updateNeighborhood,
                inputType = InputType.LETTERS_ONLY
            )

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = usuario.estado,
                    label = if (viewModel.ibgeCarregando && viewModel.estadosIbge.isEmpty()) "Carregando estados..." else "Estado",
                    onValueChange = { },
                    showSearch = true
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable {
                            if (viewModel.estadosIbge.isNotEmpty()) {
                                mostrarEstados = true
                            }
                        }
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = usuario.cidade,
                    label = if (usuario.estado.isBlank()) "Selecione o estado primeiro" else "Cidade",
                    onValueChange = { },
                    showSearch = true
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable {
                            if (usuario.estado.isNotBlank() && viewModel.cidadesIbge.isNotEmpty()) {
                                mostrarCidades = true
                            }
                        }
                )
            }

            viewModel.ibgeErroMensagem?.let { mensagem ->
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
                    navController.navigate(Routes.DADOS_PROFISSAO)
                }
            )

            if (mostrarEstados) {
                SelectionModal(
                    titulo = "Selecione o estado",
                    itens = viewModel.estadosIbge.map { estado -> estado.nome },
                    onSelecionar = { estado ->
                        viewModel.selectIbgeState(estado)
                        mostrarEstados = false
                    }
                )
            }

            if (mostrarCidades) {
                SelectionModal(
                    titulo = "Selecione a cidade",
                    itens = viewModel.cidadesIbge,
                    onSelecionar = { cidade ->
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
fun EnderecosScreenPreview() {
    EnderecosScreen(navController = rememberNavController())
}