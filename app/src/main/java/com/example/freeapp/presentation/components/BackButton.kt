package com.example.freeapp.presentation.components

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freeapp.R
import com.example.freeapp.presentation.theme.PrimaryBlue

@Composable
fun BackButton(
    onClick: () -> Unit,
    tint: Color = PrimaryBlue,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.offset(
            x = (-17).dp,
            y = 8.dp
        )
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.fi_rr_arrow_left
            ),
            contentDescription = stringResource(
                R.string.back_button_description
            ),
            tint = tint,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BackButtonPreview() {
    BackButton(
        onClick = {}
    )
}