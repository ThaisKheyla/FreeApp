package com.projeto.ui.screens.cadastro

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.freeapp.ui.theme.CheckboxBackground
import com.example.freeapp.ui.theme.PrimaryBlue
import com.projeto.ui.components.BotaoBlue
import com.projeto.ui.components.BotaoVoltar
import com.projeto.ui.components.CampoTexto
import com.projeto.ui.navigation.Routes
import com.projeto.ui.viewmodel.UsuarioViewModel

@Composable
fun DadosProfissaoScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsuarioViewModel = UsuarioViewModel()
){
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
                    navController.navigate(Routes.DADOS_PESSOAIS)
                }
            )
            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "Dados Profissionais",
                style = MaterialTheme.typography.headlineLarge
            )

            CampoTexto(
                valor = usuario.profissao,
                rotulo = "Profissão",
                onValorChange = viewModel::atualizarProfissao
            )
            CampoTexto(
                valor = usuario.especialidade,
                rotulo = "Especialidade",
                onValorChange = viewModel::atualizarEspecialidade
            )
            CampoTexto(
                valor = usuario.regiao,
                rotulo = "Qual região atende",
                onValorChange = viewModel::atualizarRegiao
            )
            CampoTexto(
                valor = usuario.horario,
                rotulo = "Qual horário tem disponibilidade",
                onValorChange = viewModel::atualizarHorario
            )
            Spacer(
                modifier = Modifier.height(32.dp)
            )

            BotaoBlue(
                texto = "CONTINUAR",
                onClick = {
                    navController.navigate(Routes.DADOS_BANCARIOS)
                }
            )
        }
    }
}