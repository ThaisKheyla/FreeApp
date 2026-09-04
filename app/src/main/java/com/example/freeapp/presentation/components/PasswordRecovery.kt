package com.example.freeapp.presentation.components

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
import com.example.freeapp.presentation.theme.PrimaryBlue

@Composable
fun PasswordRecoveryHeader(
    image: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = title,
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(180.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.68f),
            modifier = Modifier.padding(horizontal = 12.dp)
        )
    }
}

@Composable
fun PasswordRecoveryPhoneFields(
    ddd: String,
    phoneNumber: String,
    onDddChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextField(
            value = ddd,
            label = "DD",
            onValueChange = { value ->
                onDddChange(value.filter { it.isDigit() }.take(2))
            },
            modifier = Modifier.weight(0.8f)
        )

        TextField(
            value = phoneNumber,
            label = "Número",
            onValueChange = { value ->
                onPhoneNumberChange(value.filter { it.isDigit() }.take(9))
            },
            modifier = Modifier.weight(2.2f)
        )
    }
}

@Composable
fun PasswordRecoveryCodeField(
    code: String,
    onCodeChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = code,
        label = "Código de verificação",
        onValueChange = { value ->
            onCodeChange(value.filter { it.isDigit() }.take(4))
        },
        modifier = modifier
    )
}

@Composable
fun PasswordRecoveryLink(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        color = PrimaryBlue,
        textAlign = TextAlign.Center,
        modifier = modifier.clickable(onClick = onClick)
    )
}

@Composable
fun PasswordRecoveryFooter(
    buttonText: String,
    enabled: Boolean,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    linkText: String? = null,
    onLinkClick: (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BlueButton(
            text = buttonText,
            enabled = enabled,
            onClick = onButtonClick
        )

        if (linkText != null && onLinkClick != null) {
            Spacer(modifier = Modifier.height(24.dp))

            PasswordRecoveryLink(
                text = linkText,
                onClick = onLinkClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordRecoveryComponentsPreview() {
    Column(
        modifier = Modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        PasswordRecoveryHeader(
            image = R.drawable.illustration,
            title = "Esqueceu a Senha?",
            description = "Informe seu número para redefinir a sua senha."
        )

        PasswordRecoveryPhoneFields(
            ddd = "11",
            phoneNumber = "999999999",
            onDddChange = {},
            onPhoneNumberChange = {}
        )

        PasswordRecoveryCodeField(
            code = "5427",
            onCodeChange = {}
        )

        PasswordRecoveryLink(
            text = "TENTE DE OUTRA MANEIRA",
            onClick = {}
        )

        PasswordRecoveryFooter(
            buttonText = "CONTINUAR",
            enabled = true,
            onButtonClick = {},
            linkText = "TENTE DE OUTRA MANEIRA",
            onLinkClick = {}
        )
    }
}