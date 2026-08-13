package com.projeto.ui.screens.cadastro

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.projeto.ui.viewmodel.UsuarioViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.projeto.ui.components.BotaoVoltar
import com.projeto.ui.navigation.Routes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.navigation.NavController
import com.projeto.ui.components.BotaoBlueFixo
import com.projeto.ui.components.CampoTexto


@Composable
fun EnderecosScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsuarioViewModel = UsuarioViewModel()
){
    val usuario = viewModel.usuario
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

            BotaoVoltar(
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
            CampoTexto(
                valor = usuario.cep,
                rotulo = "CEP",
                onValorChange = viewModel::atualizarCep
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                CampoTexto(
                    valor = usuario.endereco,
                    rotulo = "Endereço",
                    onValorChange = viewModel::atualizarEndereco,
                    modifier = Modifier.weight(3f)
                )

                CampoTexto(
                    valor = usuario.numero,
                    rotulo = "Nº",
                    onValorChange = viewModel::atualizarNumero,
                    modifier = Modifier.weight(1f)
                )
            }


            CampoTexto(
                valor = usuario.complemento,
                rotulo = "Complemento",
                onValorChange = viewModel::atualizarComplemento
            )

            CampoTexto(
                valor = usuario.bairro,
                rotulo = "Bairro",
                onValorChange = viewModel::atualizarBairro
            )

            CampoTexto(
                valor = usuario.cidade,
                rotulo = "Cidade",
                onValorChange = viewModel::atualizarCidade,
                mostrarSearch = true
            )

            CampoTexto(
                valor = usuario.estado,
                rotulo = "Estado",
                onValorChange = viewModel::atualizarEstado,
                mostrarSearch = true
            )
            }

            BotaoBlueFixo(
                texto = "CONTINUAR",
                enabled = enderecoValido,
                onClick = {
                    navController.navigate(Routes.DADOS_PROFISSAO)
                }
            )

        }
    }
}