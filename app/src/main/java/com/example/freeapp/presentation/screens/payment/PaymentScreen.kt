package com.example.freeapp.presentation.screens.payment

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
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.components.FixedBlueButton
import com.example.freeapp.presentation.components.InputType
import com.example.freeapp.presentation.components.TextField
import com.example.freeapp.presentation.navigation.Routes
import com.example.freeapp.presentation.viewmodel.PaymentViewModel

@Composable
fun PaymentScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PaymentViewModel = PaymentViewModel()
) {
    val user = viewModel.user

    var showCardBack by remember {
        mutableStateOf(false)
    }

    val isPaymentValid =
        user.opcaoPagamento.isNotBlank() &&
                user.nome.isNotBlank() &&
                user.numeroCartao.isNotBlank() &&
                user.validadeCartao.isNotBlank() &&
                user.cvv.isNotBlank()

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
                BackButton(
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

                CardPreview(
                    name = user.nome,
                    cardNumber = user.numeroCartao,
                    cardExpiration = user.validadeCartao,
                    cardType = user.opcaoPagamento,
                    cvv = user.cvv,
                    showBack = showCardBack
                )

                TextField(
                    value = user.opcaoPagamento,
                    label = "Débito",
                    onValueChange = viewModel::updatePaymentOption,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                showCardBack = false
                            }
                        }
                )

                TextField(
                    value = user.nome,
                    label = "Nome completo",
                    onValueChange = viewModel::updateName,
                    inputType = InputType.LETTERS_ONLY,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                showCardBack = false
                            }
                        }
                )

                TextField(
                    value = user.numeroCartao,
                    label = "5999       5877     566    599",
                    onValueChange = viewModel::updateCardNumber,
                    inputType = InputType.NUMBERS_ONLY,
                    modifier = Modifier
                        .fillMaxWidth()
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                showCardBack = false
                            }
                        }
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TextField(
                        value = user.validadeCartao,
                        label = "06/2027",
                        onValueChange = viewModel::updateCardExpiration,
                        inputType = InputType.NUMBERS_AND_SLASH,
                        modifier = Modifier
                            .weight(1f)
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) {
                                    showCardBack = true
                                }
                            }
                    )

                    TextField(
                        value = user.cvv,
                        label = "915",
                        onValueChange = viewModel::updateCvv,
                        inputType = InputType.NUMBERS_ONLY,
                        modifier = Modifier
                            .weight(1f)
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) {
                                    showCardBack = true
                                }
                            }
                    )
                }
            }

            FixedBlueButton(
                text = "CONTINUAR",
                enabled = isPaymentValid,
                onClick = {
                    navController.navigate(Routes.CRIAR_SENHA)
                }
            )
        }
    }
}

@Composable
private fun CardPreview(
    name: String,
    cardNumber: String,
    cardExpiration: String,
    cardType: String,
    cvv: String,
    showBack: Boolean
) {
    val rotation by animateFloatAsState(
        targetValue = if (showBack) 180f else 0f,
        animationSpec = tween(durationMillis = 450),
        label = "cardRotation"
    )

    val isShowingFront = rotation <= 90f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
    ) {
        if (isShowingFront) {
            CardFront(
                name = name,
                cardNumber = cardNumber,
                cardExpiration = cardExpiration,
                cardType = cardType
            )
        } else {
            CardBack(
                cvv = cvv,
                cardExpiration = cardExpiration,
                modifier = Modifier.graphicsLayer {
                    rotationY = 180f
                }
            )
        }
    }
}

@Composable
private fun CardFront(
    name: String,
    cardNumber: String,
    cardExpiration: String,
    cardType: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.card_preview_background
            ),
            contentDescription = "Frente do cartão",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Text(
            text = textOrPlaceholder(
                text = cardType,
                placeholder = "Débito"
            ),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = 18.dp,
                    end = 18.dp
                )
        )

        Text(
            text = textOrPlaceholder(
                text = formatCardNumber(cardNumber),
                placeholder = "0000 0000 0000 0000"
            ),
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 42.dp
                )
                .fillMaxWidth()
        )

        Text(
            text = textOrPlaceholder(
                text = name.uppercase(),
                placeholder = "NOME COMPLETO"
            ),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = 20.dp,
                    end = 96.dp,
                    bottom = 18.dp
                )
        )

        Text(
            text = textOrPlaceholder(
                text = cardExpiration,
                placeholder = "MM/AA"
            ),
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 20.dp,
                    bottom = 18.dp
                )
        )
    }
}

@Composable
private fun CardBack(
    cvv: String,
    cardExpiration: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.card_back_preview_background
            ),
            contentDescription = "Verso do cartão",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Text(
            text = textOrPlaceholder(
                text = cardExpiration,
                placeholder = "MM/AA"
            ),
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = 20.dp,
                    bottom = 20.dp
                )
        )

        Text(
            text = textOrPlaceholder(
                text = cvv,
                placeholder = "CVV"
            ),
            color = Color(0xFF172033),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(
                    end = 36.dp,
                    bottom = 6.dp
                )
        )
    }
}

private fun formatCardNumber(
    cardNumber: String
): String {
    return cardNumber
        .filter { character ->
            character.isDigit()
        }
        .take(16)
        .chunked(4)
        .joinToString(" ")
}

private fun textOrPlaceholder(
    text: String,
    placeholder: String
): String {
    return text
        .trim()
        .takeIf { value ->
            value.isNotBlank()
        }
        ?: placeholder
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PaymentScreenPreview() {
    PaymentScreen(
        navController = rememberNavController()
    )
}