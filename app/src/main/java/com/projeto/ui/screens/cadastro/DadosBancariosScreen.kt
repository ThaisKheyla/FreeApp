package com.projeto.ui.screens.cadastro

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
import com.projeto.ui.components.BotaoVoltar
import com.projeto.ui.components.CampoTexto
import com.projeto.ui.components.OpcaoTipoConta
import com.projeto.ui.viewmodel.UsuarioViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.projeto.ui.components.BotaoBlueFixo
import com.projeto.ui.navigation.Routes

@Composable
fun DadosBancariosScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsuarioViewModel = UsuarioViewModel()
) {
    val usuario = viewModel.usuario
    var tipoContaSelecionado by remember {
        mutableStateOf("Pessoa Física")
    }
    val dadosBancariosValidos =
        usuario.agencia.isNotBlank() &&
                usuario.conta.isNotBlank() &&
                usuario.tipoConta.isNotBlank() &&
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
            BotaoVoltar(
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
            CampoTexto(
                valor = usuario.agencia,
                rotulo = "Agência",
                onValorChange = viewModel::atualizarAgencia

            )
            CampoTexto(
                valor = usuario.conta,
                rotulo = "Conta",
                onValorChange = viewModel::atualizarConta
            )
            CampoTexto(
                valor = usuario.tipoConta,
                rotulo = "Qual é o seu tipo de conta",
                onValorChange = viewModel::atualizarTipoConta
            )

            OpcaoTipoConta(
                texto = "Pessoa Física",
                selecionado = tipoContaSelecionado == "Pessoa Física",
                onSelecionar = {
                    tipoContaSelecionado = "Pessoa Física"
                    viewModel.atualizarTipoConta("Pessoa Física")
                }
            )

            OpcaoTipoConta(
                texto = "Pessoa Jurídica",
                selecionado = tipoContaSelecionado == "Pessoa Jurídica",
                onSelecionar = {
                    tipoContaSelecionado = "Pessoa Jurídica"
                    viewModel.atualizarTipoConta("Pessoa Jurídica")
                }
            )
            CampoTexto(
                valor = usuario.pix,
                rotulo = "PIX",
                onValorChange = viewModel::atualizarPix
            )
            }

            BotaoBlueFixo(
                texto = "CONTINUAR",
                enabled = dadosBancariosValidos,
                onClick = {
                    navController.navigate(Routes.FORMA_PAGAMENTO)
                }
            )

        }
    }
}