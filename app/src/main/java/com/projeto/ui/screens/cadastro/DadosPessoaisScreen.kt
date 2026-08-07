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
fun DadosPessoaisScreen(
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
                onClick = { }
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

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
                onValorChange = viewModel::atualizarEmail,
                mostrarCheck = true
            )

            CampoTexto(
                valor = usuario.confirmarEmail,
                rotulo = "Confirme seu e-mail",
                onValorChange = viewModel::atualizarConfirmarEmail,
                mostrarCheck = true
            )

            CampoTexto(
                valor = usuario.telefone,
                rotulo = "Número com DD",
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

                Spacer(
                    modifier = Modifier.width(4.dp)
                )

                val annotatedText = buildAnnotatedString {

                    append("Li e concordo com os ")

                    pushStringAnnotation(
                        tag = "TERMS",
                        annotation = "termos"
                    )

                    withStyle(
                        SpanStyle(
                            color = PrimaryBlue,
                            fontWeight = FontWeight.Normal
                        )
                    )

                    {
                        append("Termo de Uso e Política de Privacidade")
                    }

                    pop()
                }

                ClickableText(
                    style = MaterialTheme.typography.labelMedium,
                    text = annotatedText,
                    onClick = { offset ->

                        annotatedText
                            .getStringAnnotations(
                                tag = "TERMS",
                                start = offset,
                                end = offset
                            )
                            .firstOrNull()
                            ?.let {

                                // abrir tela de termos aqui

                            }
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            BotaoBlue(
                texto = "CONTINUAR",
                onClick = {
                    navController.navigate(Routes.ENDERECOS)
                }
            )
        }
    }
}