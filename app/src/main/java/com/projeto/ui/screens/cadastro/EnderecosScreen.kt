package com.projeto.ui.screens.cadastro

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
import com.projeto.ui.viewmodel.UsuarioViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.projeto.ui.components.BotaoVoltar
import com.projeto.ui.navigation.Routes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.projeto.ui.components.BotaoBlueFixo
import com.projeto.ui.components.CampoTexto
import com.projeto.ui.components.SelectionModal
import com.projeto.ui.components.TipoEntrada

@Composable
fun EnderecosScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsuarioViewModel = UsuarioViewModel()
){
    val usuario = viewModel.usuario
    var mostrarEstados by remember { mutableStateOf(false) }
    var mostrarCidades by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.carregarEstadosIbge()
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
                onValorChange = viewModel::atualizarCep,
                tipoEntrada = TipoEntrada.APENAS_NUMEROS
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
                    tipoEntrada = TipoEntrada.APENAS_NUMEROS,
                    maxLength = 10,
                    modifier = Modifier.weight(1.4f)
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
                onValorChange = viewModel::atualizarBairro,
                tipoEntrada = TipoEntrada.APENAS_LETRAS
            )

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                CampoTexto(
                    valor = usuario.estado,
                    rotulo = if (viewModel.ibgeCarregando && viewModel.estadosIbge.isEmpty()) "Carregando estados..." else "Estado",
                    onValorChange = { },
                    mostrarSearch = true
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
                CampoTexto(
                    valor = usuario.cidade,
                    rotulo = if (usuario.estado.isBlank()) "Selecione o estado primeiro" else "Cidade",
                    onValorChange = { },
                    mostrarSearch = true
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

            BotaoBlueFixo(
                texto = "CONTINUAR",
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
                        viewModel.selecionarEstadoIbge(estado)
                        mostrarEstados = false
                    }
                )
            }

            if (mostrarCidades) {
                SelectionModal(
                    titulo = "Selecione a cidade",
                    itens = viewModel.cidadesIbge,
                    onSelecionar = { cidade ->
                        viewModel.atualizarCidade(cidade)
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