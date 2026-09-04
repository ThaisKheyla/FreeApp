package com.example.freeapp.presentation.screens.ForgotPassword

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.R
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.components.PasswordField
import com.example.freeapp.presentation.components.PasswordRecoveryCodeField
import com.example.freeapp.presentation.components.PasswordRecoveryFooter
import com.example.freeapp.presentation.components.PasswordRecoveryHeader
import com.example.freeapp.presentation.components.PasswordRecoveryLink
import com.example.freeapp.presentation.components.PasswordRecoveryPhoneFields
import com.example.freeapp.presentation.components.TextField
import com.example.freeapp.presentation.navigation.Routes
import com.example.freeapp.presentation.viewmodel.UsuarioViewModel


@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: UsuarioViewModel = UsuarioViewModel()
) {
    val telefoneSalvo = viewModel.usuario.telefone.filter { it.isDigit() }
    val mensagemErroAuth = viewModel.authErroMensagem
    val carregandoAuth = viewModel.authCarregando
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
                BackButton(
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

                PasswordRecoveryHeader(
                    image = etapa.imagem,
                    title = etapa.titulo,
                    description = etapa.descricao
                )

                Spacer(modifier = Modifier.height(42.dp))
                when (etapa) {
                    EtapaRecuperacaoSenha.TELEFONE -> {
                        PasswordRecoveryPhoneFields(
                            ddd = ddd,
                            phoneNumber = numeroTelefone,
                            onDddChange = { ddd = it },
                            onPhoneNumberChange = { numeroTelefone = it }
                        )
                    }

                    EtapaRecuperacaoSenha.EMAIL -> {
                        TextField(
                            value = email,
                            label = "E-mail",
                            onValueChange = {
                                email = it
                                viewModel.limparEstadoAuth()
                            }
                        )
                    }

                    EtapaRecuperacaoSenha.CODIGO_SMS,
                    EtapaRecuperacaoSenha.CODIGO_EMAIL -> {
                        PasswordRecoveryCodeField(
                            code = codigo,
                            onCodeChange = { codigo = it }
                        )

                        Spacer(modifier = Modifier.height(18.dp))
                        PasswordRecoveryLink(
                            text = "Não recebeu um código? Reenviar",
                            onClick = { codigo = "" }
                        )
                    }

                    EtapaRecuperacaoSenha.NOVA_SENHA -> {
                        PasswordField(
                            value = novaSenha,
                            label = "Nova Senha",
                            onValueChange = {
                                novaSenha = it
                                viewModel.limparEstadoAuth()
                            }
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        PasswordField(
                            value = confirmarSenha,
                            label = "Confirme sua Senha",
                            onValueChange = {
                                confirmarSenha = it
                                viewModel.limparEstadoAuth()
                            },
                            isError = confirmarSenha.isNotBlank() &&
                                    confirmarSenha != novaSenha
                        )
                    }

                    EtapaRecuperacaoSenha.SUCESSO -> Unit
                }

                if (mensagemErroAuth != null) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = mensagemErroAuth,
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            PasswordRecoveryFooter(
                buttonText = if (
                    etapa == EtapaRecuperacaoSenha.NOVA_SENHA &&
                    carregandoAuth
                ) {
                    "REDEFININDO..."
                } else {
                    etapa.textoBotao
                },
                enabled = when (etapa) {
                    EtapaRecuperacaoSenha.TELEFONE -> telefoneValido
                    EtapaRecuperacaoSenha.EMAIL -> email.isNotBlank()
                    EtapaRecuperacaoSenha.CODIGO_SMS,
                    EtapaRecuperacaoSenha.CODIGO_EMAIL -> codigo.length == 4
                    EtapaRecuperacaoSenha.NOVA_SENHA -> senhasIguais && !carregandoAuth
                    EtapaRecuperacaoSenha.SUCESSO -> true
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        start = 14.dp,
                        end = 14.dp,
                        bottom = 22.dp
                    ),
                linkText = if (
                    etapa == EtapaRecuperacaoSenha.TELEFONE
                ) {
                    "TENTE DE OUTRA MANEIRA"
                } else {
                    null
                },
                onLinkClick = if (
                    etapa == EtapaRecuperacaoSenha.TELEFONE
                ) {
                    {
                        etapa = EtapaRecuperacaoSenha.EMAIL
                        codigo = ""
                    }
                } else {
                    null
                },
                onButtonClick = {
                    when (etapa) {
                        EtapaRecuperacaoSenha.TELEFONE -> etapa = EtapaRecuperacaoSenha.CODIGO_SMS
                            EtapaRecuperacaoSenha.EMAIL -> {
                                viewModel.redefinirSenha(
                                    email = email
                                ) {
                                    etapa = EtapaRecuperacaoSenha.SUCESSO
                                }
                            }
                        EtapaRecuperacaoSenha.CODIGO_SMS,
                        EtapaRecuperacaoSenha.CODIGO_EMAIL -> {
                            etapaCodigoAnterior = etapa
                            etapa = EtapaRecuperacaoSenha.NOVA_SENHA
                        }
                        EtapaRecuperacaoSenha.NOVA_SENHA -> {
                            val emailRecuperacao = email.ifBlank { viewModel.usuario.email }
                            viewModel.redefinirSenha(
                                email = emailRecuperacao,
                            ) {
                                etapa = EtapaRecuperacaoSenha.SUCESSO
                            }
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


