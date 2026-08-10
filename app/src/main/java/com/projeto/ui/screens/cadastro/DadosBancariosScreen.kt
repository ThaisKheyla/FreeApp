package com.projeto.ui.screens.cadastro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.projeto.ui.components.BotaoVoltar
import com.projeto.ui.components.CampoTexto
import com.projeto.ui.navigation.Routes
import com.projeto.ui.viewmodel.UsuarioViewModel

@Composable
fun DadosBancariosScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsuarioViewModel = UsuarioViewModel()
) {
    val usuario = viewModel.usuario
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(22.dp)
        ) {
            BotaoVoltar(
                onClick = {
//                    navController.navigate(Routes.DADOS_PESSOAIS)
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
                valor = usuario.profissao,
                rotulo = "Profissão",
                onValorChange = viewModel::atualizarProfissao
            )
            CampoTexto(
                valor = usuario.profissao,
                rotulo = "Profissão",
                onValorChange = viewModel::atualizarProfissao
            )
            CampoTexto(
                valor = usuario.profissao,
                rotulo = "Profissão",
                onValorChange = viewModel::atualizarProfissao
            )
            CampoTexto(
                valor = usuario.profissao,
                rotulo = "Profissão",
                onValorChange = viewModel::atualizarProfissao
            )

        }
    }
}