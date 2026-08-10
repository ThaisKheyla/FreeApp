
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
import com.projeto.ui.components.BotaoVoltar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import com.example.freeapp.ui.theme.CheckboxBackground
import com.example.freeapp.ui.theme.PrimaryBlue
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.freeapp.ui.theme.FreeAppTheme
import com.projeto.ui.components.BotaoBlue

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
            BotaoVoltar(
                onClick = { }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Dados Pessoais",
                style = MaterialTheme.typography.headlineLarge
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
                valor = "",
                rotulo = "Confirme seu e-mail",
                onValorChange = { }
            )
            CampoTexto(
                valor = usuario.telefone,
                rotulo = "Telefone",
                onValorChange = viewModel::atualizarTelefone
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    modifier = Modifier.size(20.dp),
                    checked = false,
                    onCheckedChange = { },
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = CheckboxBackground,
                        checkedColor = PrimaryBlue
                    )
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Li e estou de acordo com o Termo de Uso e Política de Privacidade",
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            BotaoBlue(
                texto = "CONTINUAR",
                onClick = { }
            )

        }
    }
}

