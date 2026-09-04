package com.example.freeapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.freeapp.presentation.theme.TextFieldBackground
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.freeapp.R
import com.example.freeapp.presentation.theme.PrimaryBlue

enum class InputType {
    FREE,
    LETTERS_ONLY,
    NUMBERS_ONLY,
    NUMBERS_AND_SLASH
}

@Composable
fun TextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    showCheck: Boolean = false,
    showSearch: Boolean = false,
    isError: Boolean = false,
    inputType: InputType = InputType.FREE,
    maxLength: Int? = null
) {

    OutlinedTextField(
        value = value,
        onValueChange = { newValue ->
            val filteredValue = newValue.filter { character ->
                when (inputType) {
                    InputType.FREE -> true
                    InputType.LETTERS_ONLY -> character.isLetter() || character == ' '
                    InputType.NUMBERS_ONLY -> character.isDigit()
                    InputType.NUMBERS_AND_SLASH -> character.isDigit() || character == '/'
                }
            }

            onValueChange(
                if (maxLength != null) filteredValue.take(maxLength)
                else filteredValue
            )
        },

        keyboardOptions = KeyboardOptions(
            keyboardType = when (inputType) {
                InputType.NUMBERS_ONLY,
                InputType.NUMBERS_AND_SLASH -> KeyboardType.Number

                InputType.LETTERS_ONLY,
                InputType.FREE -> KeyboardType.Text
            }
        ),

        placeholder = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall
            )
        },

        trailingIcon = {
            if (showCheck) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.fi_rr_check
                    ),
                    contentDescription = "Valid field",
                    tint = PrimaryBlue,
                    modifier = Modifier.size(18.dp)
                )
            }

            if (showSearch) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.fi_rr_search
                    ),
                    contentDescription = "Search",
                    tint = PrimaryBlue,
                    modifier = Modifier.size(18.dp)
                )
            }
        },

        isError = isError,

        modifier = modifier.height(55.dp),

        shape = RoundedCornerShape(10.dp),

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor =
                if (isError) Color.Red else Color.Transparent,

            unfocusedBorderColor =
                if (isError) Color.Red else Color.Transparent,

            focusedContainerColor = TextFieldBackground,
            unfocusedContainerColor = TextFieldBackground
        )
    )
}