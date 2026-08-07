package com.projeto.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.projeto.ui.components.CampoTexto
import com.projeto.ui.viewmodel.UsuarioViewModel

@Composable
fun DadosPessoaisScreen(
    modifier: Modifier = Modifier,
    viewModel: UsuarioViewModel = UsuarioViewModel()
) {
    val usuario = viewModel.usuario

    Surface(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Dados Pessoais",
                style = MaterialTheme.typography.headlineMedium
            )
            CampoTexto(
                valor = usuario.nome,
                rotulo = "Nome completo",
                onValorChange = viewModel::atualizarNome
            )
            CampoTexto(
                valor = usuario.dataNascimento,
                rotulo = "Data de nascimento",
                onValorChange = viewModel::atualizarDataNascimento
            )
            CampoTexto(
                valor = usuario.cpf,
                rotulo = "CPF",
                onValorChange = viewModel::atualizarCpf
            )
            CampoTexto(
                valor = usuario.email,
                rotulo = "E-mail",
                onValorChange = viewModel::atualizarEmail
            )
            CampoTexto(
                valor = usuario.telefone,
                rotulo = "Telefone",
                onValorChange = viewModel::atualizarTelefone
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "CONTINUAR")
            }
        }
    }
}