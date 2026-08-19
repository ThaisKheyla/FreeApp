package com.projeto.ui.screens.senha

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.R
import com.projeto.ui.components.BotaoVoltar
import com.projeto.ui.components.CabecalhoRecuperacaoSenha
import com.projeto.ui.components.CampoCodigoRecuperacao
import com.projeto.ui.components.CampoSenha
import com.projeto.ui.components.CampoTexto
import com.projeto.ui.components.CamposTelefoneRecuperacao
import com.projeto.ui.components.LinkRecuperacaoSenha
import com.projeto.ui.components.RodapeRecuperacaoSenha
import com.projeto.ui.navigation.Routes
import com.projeto.ui.viewmodel.UsuarioViewModel

@Composable
fun EsqueciSenhaScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsuarioViewModel = UsuarioViewModel()
) {
    val telefoneSalvo = viewModel.usuario.telefone.filter {it.isDigit()}
    var etapa by remember { mutableStateOf(EtapaRecuperacaoSenha.TELEFONE) }
    var etapaCodigoAnterior by remember { mutableStateOf(EtapaRecuperacaoSenha.CODIGO_SMS) }
    var ddd by remember(telefoneSalvo) { mutableStateOf(telefoneSalvo.take(2)) }
    var numeroTelefone by remember(telefoneSalvo) { mutableStateOf(telefoneSalvo.drop(2).take(9)) }
    var email by remember(viewModel.usuario.email) { mutableStateOf(viewModel.usuario.email) }
    var codigo by remember { mutableStateOf("") }
    var novaSenha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }
    val telefoneValido = ddd.length == 2 && numeroTelefone.length >= 8
    val senhasIguais = novaSenha.isNotBlank() && novaSenha == confirmarSenha

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
                    .padding(horizontal = 14.dp)
                    .padding(top = 42.dp, bottom = 148.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BotaoVoltar(
                    onClick = {
                        if (etapa == EtapaRecuperacaoSenha.TELEFONE) {
                            navController.popBackStack()
                        } else if (etapa == EtapaRecuperacaoSenha.NOVA_SENHA) {
                            etapa = etapaCodigoAnterior
                        } else {
                            etapa = etapa.anterior()
                            codigo = ""
                        }
                    },
                    modifier = Modifier.align(Alignment.Start)
                )

                Spacer(modifier = Modifier.height(14.dp))

                CabecalhoRecuperacaoSenha(
                    imagem = etapa.imagem,
                    titulo = etapa.titulo,
                    descricao = etapa.descricao
                )

                Spacer(modifier = Modifier.height(42.dp))
                when (etapa) {
                    EtapaRecuperacaoSenha.TELEFONE -> {
                        CamposTelefoneRecuperacao(
                            ddd = ddd,
                            numero = numeroTelefone,
                            onDddChange = { ddd = it },
                            onNumeroChange = { numeroTelefone = it }
                        )
                    }

                    EtapaRecuperacaoSenha.EMAIL -> {
                        CampoTexto(
                            valor = email,
                            rotulo = "E-mail",
                            onValorChange = { email = it }
                        )
                    }

                    EtapaRecuperacaoSenha.CODIGO_SMS,
                    EtapaRecuperacaoSenha.CODIGO_EMAIL -> {
                        CampoCodigoRecuperacao(
                            codigo = codigo,
                            onCodigoChange = { codigo = it }
                        )

                        Spacer(modifier = Modifier.height(18.dp))
                        LinkRecuperacaoSenha(
                            texto = "Não recebeu um código? Reenviar",
                            onClick = { codigo = "" }
                        )
                    }

                    EtapaRecuperacaoSenha.NOVA_SENHA -> {
                        CampoSenha(
                            valor = novaSenha,
                            rotulo = "Nova Senha",
                            onValorChange = { novaSenha = it }
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        CampoSenha(
                            valor = confirmarSenha,
                            rotulo = "Confirme sua Senha",
                            onValorChange = { confirmarSenha = it },
                            isError = confirmarSenha.isNotBlank() && confirmarSenha != novaSenha
                        )
                    }

                    EtapaRecuperacaoSenha.SUCESSO -> Unit
                }
            }

            RodapeRecuperacaoSenha(
                textoBotao = etapa.textoBotao,
                enabled = when (etapa) {
                    EtapaRecuperacaoSenha.TELEFONE -> telefoneValido
                    EtapaRecuperacaoSenha.EMAIL -> email.isNotBlank()
                    EtapaRecuperacaoSenha.CODIGO_SMS,
                    EtapaRecuperacaoSenha.CODIGO_EMAIL -> codigo.length == 4
                    EtapaRecuperacaoSenha.NOVA_SENHA -> senhasIguais
                    EtapaRecuperacaoSenha.SUCESSO -> true
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(start = 14.dp, end = 14.dp, bottom = 22.dp),
                textoLink = if (etapa == EtapaRecuperacaoSenha.TELEFONE) "TENTE DE OUTRA MANEIRA" else null,
                onLinkClick = if (etapa == EtapaRecuperacaoSenha.TELEFONE) {
                    {
                        etapa = EtapaRecuperacaoSenha.EMAIL
                        codigo = ""
                    }
                } else {
                    null
                },
                onBotaoClick = {
                    when (etapa) {
                        EtapaRecuperacaoSenha.TELEFONE -> etapa = EtapaRecuperacaoSenha.CODIGO_SMS
                        EtapaRecuperacaoSenha.EMAIL -> etapa = EtapaRecuperacaoSenha.CODIGO_EMAIL
                        EtapaRecuperacaoSenha.CODIGO_SMS,
                        EtapaRecuperacaoSenha.CODIGO_EMAIL -> {
                            etapaCodigoAnterior = etapa
                            etapa = EtapaRecuperacaoSenha.NOVA_SENHA
                        }
                        EtapaRecuperacaoSenha.NOVA_SENHA -> {
                            viewModel.atualizarSenha(novaSenha)
                            etapa = EtapaRecuperacaoSenha.SUCESSO
                        }
                        EtapaRecuperacaoSenha.SUCESSO -> {
                            navController.navigate(Routes.LOGIN) {
                                popUpTo(Routes.LOGIN)
                                launchSingleTop = true
                            }
                        }
                    }
                }
            )
        }
    }
}

private enum class EtapaRecuperacaoSenha(
    val titulo: String,
    val descricao: String,
    val textoBotao: String,
    val imagem: Int
) {
    TELEFONE(
        titulo = "Esqueceu a Senha?",
        descricao = "Não se preocupe, nós vamos te ajudar! Informe o número para o qual deseja redefinir a sua senha.",
        textoBotao = "CONTINUAR",
        imagem = R.drawable.illustration
    ),
    EMAIL(
        titulo = "Esqueci minha senha E-mail",
        descricao = "Informe seu e-mail para receber o código de redefinição.",
        textoBotao = "CONTINUAR",
        imagem = R.drawable.illustration
    ),
    CODIGO_SMS(
        titulo = "Verifique seu celular",
        descricao = "Acabamos de enviar um código para o seu número de telefone.",
        textoBotao = "VERIFICAR",
        imagem = R.drawable.illustration__1_
    ),
    CODIGO_EMAIL(
        titulo = "Verifique seu E-mail",
        descricao = "Acabamos de enviar um código para o seu e-mail.",
        textoBotao = "VERIFICAR",
        imagem = R.drawable.illustration__1_
    ),
    NOVA_SENHA(
        titulo = "Redefina sua senha",
        descricao = "Preencha os campos abaixo.",
        textoBotao = "REDEFINIR SENHA",
        imagem = R.drawable.illustration__2_
    ),
    SUCESSO(
        titulo = "Redefinido com sucesso",
        descricao = "Agora você pode fazer login na sua conta.",
        textoBotao = "CONECTE-SE AGORA",
        imagem = R.drawable.illustration__3_
    );

    fun anterior(): EtapaRecuperacaoSenha {
        return when (this) {
            TELEFONE -> TELEFONE
            EMAIL -> TELEFONE
            CODIGO_SMS -> TELEFONE
            CODIGO_EMAIL -> EMAIL
            NOVA_SENHA -> CODIGO_SMS
            SUCESSO -> NOVA_SENHA
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EsqueciSenhaScreenPreview() {
    EsqueciSenhaScreen(navController = rememberNavController())
}
