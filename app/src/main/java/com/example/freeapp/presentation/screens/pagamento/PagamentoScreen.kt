package com.example.freeapp.presentation.screens.pagamento

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.R
import com.projeto.ui.components.BotaoBlueFixo
import com.projeto.ui.components.BotaoVoltar
import com.projeto.ui.components.CampoTexto
import com.projeto.ui.components.TipoEntrada
import com.projeto.ui.navigation.Routes
import com.projeto.ui.viewmodel.UsuarioViewModel

@Composable
fun PagamentoScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsuarioViewModel = UsuarioViewModel()
) {
    val usuario = viewModel.usuario
    var mostrarVersoCartao by remember { mutableStateOf(false) }
    val pagamentoValido =
        usuario.opcaoPagamento.isNotBlank() &&
                usuario.nome.isNotBlank() &&
                usuario.numeroCartao.isNotBlank() &&
                usuario.validadeCartao.isNotBlank() &&
                usuario.cvv.isNotBlank()

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
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Formas de Pagamento",
                    style = MaterialTheme.typography.headlineLarge
                )
                CartaoPreview(
                    nome = usuario.nome,
                    numeroCartao = usuario.numeroCartao,
                    validadeCartao = usuario.validadeCartao,
                    tipoCartao = usuario.opcaoPagamento,
                    cvv = usuario.cvv,
                    mostrarVerso = mostrarVersoCartao
                )

                CampoTexto(
                    valor = usuario.opcaoPagamento,
                    rotulo = "Débito",
                    onValorChange = viewModel::atualizarOpcaoPagamento,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) mostrarVersoCartao = false
                        }
                )
                CampoTexto(
                    valor = usuario.nome,
                    rotulo = "Nome completo",
                    onValorChange = viewModel::atualizarNome,
                    tipoEntrada = TipoEntrada.APENAS_LETRAS,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) mostrarVersoCartao = false
                        }
                )
                CampoTexto(
                    valor = usuario.numeroCartao,
                    rotulo = "5999       5877     566    599",
                    onValorChange = viewModel::atualizarNumeroCartao,
                    tipoEntrada = TipoEntrada.APENAS_NUMEROS,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) mostrarVersoCartao = false
                        }
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CampoTexto(
                        valor = usuario.validadeCartao,
                        rotulo = "06/2027",
                        onValorChange = viewModel::atualizarValidadeCartao,
                        tipoEntrada = TipoEntrada.NUMEROS_E_BARRA,
                        modifier = Modifier
                            .weight(1f)
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) mostrarVersoCartao = true
                            }
                    )

                    CampoTexto(
                        valor = usuario.cvv,
                        rotulo = "915",
                        onValorChange = viewModel::atualizarCvv,
                        tipoEntrada = TipoEntrada.APENAS_NUMEROS,
                        modifier = Modifier
                            .weight(1f)
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) mostrarVersoCartao = true
                            }
                    )
                }
            }

            BotaoBlueFixo(
                texto = "CONTINUAR",
                enabled = pagamentoValido,
                onClick = {
                    navController.navigate(Routes.CRIAR_SENHA)
                }
            )
        }
    }
}

@Composable
private fun CartaoPreview(
    nome: String,
    numeroCartao: String,
    validadeCartao: String,
    tipoCartao: String,
    cvv: String,
    mostrarVerso: Boolean
) {
    val rotacao by animateFloatAsState(
        targetValue = if (mostrarVerso) 180f else 0f,
        animationSpec = tween(durationMillis = 450),
        label = "rotacaoCartao"
    )

    val mostrandoFrente = rotacao <= 90f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .graphicsLayer {
                rotationY = rotacao
                cameraDistance = 12f * density
            }
    ) {
        if (mostrandoFrente) {
            CartaoFrente(
                nome = nome,
                numeroCartao = numeroCartao,
                validadeCartao = validadeCartao,
                tipoCartao = tipoCartao
            )
        } else {
            CartaoVerso(
                cvv = cvv,
                validadeCartao = validadeCartao,
                modifier = Modifier.graphicsLayer { rotationY = 180f }
            )
        }
    }
}

@Composable
private fun CartaoFrente(
    nome: String,
    numeroCartao: String,
    validadeCartao: String,
    tipoCartao: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.card_preview_background),
            contentDescription = "Frente do cartão",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Text(
            text = textoOuPlaceholder(tipoCartao, "Débito"),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 18.dp, end = 18.dp)
        )

        Text(
            text = textoOuPlaceholder(formatarNumeroCartao(numeroCartao), "0000 0000 0000 0000"),
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, end = 20.dp, bottom = 42.dp)
                .fillMaxWidth()
        )

        Text(
            text = textoOuPlaceholder(nome.uppercase(), "NOME COMPLETO"),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, end = 96.dp, bottom = 18.dp)
        )

        Text(
            text = textoOuPlaceholder(validadeCartao, "MM/AA"),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 18.dp)
        )
    }
}

@Composable
private fun CartaoVerso(
    cvv: String,
    validadeCartao: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.card_back_preview_background),
            contentDescription = "Verso do cartão",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Text(
            text = textoOuPlaceholder(validadeCartao, "MM/AA"),
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 20.dp)
        )

        Text(
            text = textoOuPlaceholder(cvv, "CVV"),
            color = Color(0xFF172033),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 36.dp, bottom = 6.dp)
        )
    }
}

private fun formatarNumeroCartao(numeroCartao: String): String {
    return numeroCartao
        .filter { caractere -> caractere.isDigit() }
        .take(16)
        .chunked(4)
        .joinToString(" ")
}

private fun textoOuPlaceholder(texto: String, placeholder: String): String {
    return texto.trim().takeIf { valor -> valor.isNotBlank() } ?: placeholder
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PagamentoScreenPreview() {
    PagamentoScreen(navController = rememberNavController())
}