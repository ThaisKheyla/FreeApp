package com.example.freeapp.presentation.screens.cadastro

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.components.FixedBlueButton
import com.example.freeapp.presentation.components.InputType
import com.example.freeapp.presentation.components.TextField
import com.example.freeapp.presentation.navigation.Routes
import com.example.freeapp.presentation.viewmodel.UsuarioViewModel

@Composable
fun DadosProfissaoScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
        viewModel: com.example.freeapp.presentation.viewmodel.UsuarioViewModel = UsuarioViewModel()
){
    val usuario = viewModel.usuario
    val dadosProfissionaisValidos =
        usuario.profissao.isNotBlank() &&
                usuario.especialidade.isNotBlank() &&
                usuario.regiao.isNotBlank() &&
                usuario.horario.isNotBlank()

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
                text = "Dados Profissionais",
                style = MaterialTheme.typography.headlineLarge
            )

            TextField(
                value = usuario.profissao,
                label = "Profissão",
                onValueChange = viewModel::updateProfession,
                inputType = InputType.LETTERS_ONLY
            )
            TextField(
                value = usuario.especialidade,
                label = "Especialidade",
                onValueChange = viewModel::updateSpecialty,
                inputType = InputType.LETTERS_ONLY
            )
            TextField(
                value = usuario.regiao,
                label = "Qual região atende",
                onValueChange = viewModel::updateRegion,
                inputType = InputType.LETTERS_ONLY
            )
            TextField(
                value = usuario.horario,
                label = "Qual horário tem disponibilidade",
                onValueChange = viewModel::updateSchedule
            )
            }

            FixedBlueButton(
                text = "CONTINUAR",
                enabled = dadosProfissionaisValidos,
                onClick = {
                    navController.navigate(Routes.DADOS_BANCARIOS)
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DadosProfissaoScreenPreview() {
    DadosProfissaoScreen(navController = rememberNavController())
}