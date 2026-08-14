package com.projeto.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freeapp.R
import com.example.freeapp.ui.theme.PrimaryBlue

@Composable
fun CabecalhoRecuperacaoSenha(
    imagem: Int,
    titulo: String,
    descricao: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imagem),
            contentDescription = titulo,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(180.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = titulo,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = descricao,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.68f),
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}

@Composable
fun CamposTelefoneRecuperacao(
    ddd: String,
    numero: String,
    onDddChange: (String) -> Unit,
    onNumeroChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CampoTexto(
            valor = ddd,
            rotulo = "DD",
            onValorChange = { valor -> onDddChange(valor.filter { it.isDigit() }.take(2)) },
            modifier = Modifier.weight(0.8f)
        )

        CampoTexto(
            valor = numero,
            rotulo = "Número",
            onValorChange = { valor -> onNumeroChange(valor.filter { it.isDigit() }.take(9)) },
            modifier = Modifier.weight(2.2f)
        )
    }
}

@Composable
fun CampoCodigoRecuperacao(
    codigo: String,
    onCodigoChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    CampoTexto(
        valor = codigo,
        rotulo = "Código de verificação",
        onValorChange = { valor -> onCodigoChange(valor.filter { it.isDigit() }.take(4)) },
        modifier = modifier
    )
}

@Composable
fun LinkRecuperacaoSenha(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        style = MaterialTheme.typography.bodySmall,
        color = PrimaryBlue,
        textAlign = TextAlign.Center,
        modifier = modifier.clickable(onClick = onClick)
    )
}

@Composable
fun RodapeRecuperacaoSenha(
    textoBotao: String,
    enabled: Boolean,
    onBotaoClick: () -> Unit,
    modifier: Modifier = Modifier,
    textoLink: String? = null,
    onLinkClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BotaoBlue(
            texto = textoBotao,
            enabled = enabled,
            onClick = onBotaoClick
        )

        if (textoLink != null && onLinkClick != null) {
            Spacer(modifier = Modifier.height(24.dp))
            LinkRecuperacaoSenha(
                texto = textoLink,
                onClick = onLinkClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecuperacaoSenhaComponentsPreview() {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        CabecalhoRecuperacaoSenha(
            imagem = R.drawable.illustration,
            titulo = "Esqueceu a Senha?",
            descricao = "Informe seu número para redefinir a sua senha."
        )
        CamposTelefoneRecuperacao(
            ddd = "11",
            numero = "999999999",
            onDddChange = { },
            onNumeroChange = { }
        )
        CampoCodigoRecuperacao(
            codigo = "5427",
            onCodigoChange = { }
        )
        LinkRecuperacaoSenha(
            texto = "TENTE DE OUTRA MANEIRA",
            onClick = { }
        )
        RodapeRecuperacaoSenha(
            textoBotao = "CONTINUAR",
            enabled = true,
            onBotaoClick = { },
            textoLink = "TENTE DE OUTRA MANEIRA",
            onLinkClick = { }
        )
    }
}