package com.example.freeapp.presentation.view.register.personal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.R
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.components.FixedBlueButton
import com.example.freeapp.presentation.components.InputType
import com.example.freeapp.presentation.components.TermsModal
import com.example.freeapp.presentation.components.TextField
import com.example.freeapp.presentation.navigation.Routes
import com.example.freeapp.presentation.theme.CheckboxBackground
import com.example.freeapp.presentation.theme.PrimaryBlue
import com.example.freeapp.presentation.validation.UserValidator
import com.example.freeapp.presentation.viewmodel.PersonalDataViewModel

@Composable
fun PersonalDataScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PersonalDataViewModel = PersonalDataViewModel()
) {
    val user = viewModel.user

    var showTerms by remember {
        mutableStateOf(false)
    }

    var hasAcceptedTerms by remember {
        mutableStateOf(false)
    }

    val isCpfValid =
        UserValidator.isValidCpf(
            user.cpf
        )

    val isEmailValid =
        UserValidator.isValidEmail(
            user.email
        )

    val areEmailsEqual =
        UserValidator.areEmailsEqual(
            email = user.email,
            confirmEmail = user.confirmEmail
        )

    val isPhoneValid =
        UserValidator.isValidPhone(
            user.phone
        )

    val isNameValid =
        UserValidator.isValidName(
            user.name
        )

    val isBirthDateValid =
        UserValidator.isValidBirthDate(
            user.birthDate
        )

    val isPersonalDataValid =
        isNameValid &&
                isBirthDateValid &&
                isCpfValid &&
                isEmailValid &&
                areEmailsEqual &&
                isPhoneValid &&
                hasAcceptedTerms

    val termsPrefix = stringResource(
        R.string.personal_data_terms_prefix
    )

    val termsLink = stringResource(
        R.string.personal_data_terms_link
    )

    val annotatedTermsText = buildAnnotatedString {
        append(termsPrefix)
        append(" ")

        pushStringAnnotation(
            tag = "TERMS",
            annotation = "termos"
        )

        withStyle(
            style = SpanStyle(
                color = PrimaryBlue,
                fontWeight = FontWeight.Normal
            )
        ) {
            append(termsLink)
        }

        pop()
    }

    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
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
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = stringResource(
                        R.string.personal_data_title
                    ),
                    style = MaterialTheme.typography.headlineLarge
                )

                TextField(
                    value = user.name,
                    label = stringResource(
                        R.string.personal_data_full_name
                    ),
                    onValueChange = viewModel::updateName,
                    inputType = InputType.LETTERS_ONLY,
                    showCheck =
                        isNameValid &&
                                user.name.isNotBlank(),
                    isError =
                        user.name.isNotBlank() &&
                                !isNameValid
                )

                TextField(
                    value = user.birthDate,
                    label = stringResource(
                        R.string.personal_data_birth_date
                    ),
                    onValueChange = viewModel::updateBirthDate,
                    inputType = InputType.NUMBERS_AND_SLASH,
                    showCheck =
                        isBirthDateValid &&
                                user.birthDate.isNotBlank(),
                    isError =
                        user.birthDate.isNotBlank() &&
                                !isBirthDateValid
                )

                TextField(
                    value = user.cpf,
                    label = stringResource(
                        R.string.personal_data_cpf
                    ),
                    onValueChange = viewModel::updateCpf,
                    inputType = InputType.NUMBERS_ONLY,
                    showCheck =
                        isCpfValid &&
                                user.cpf.isNotBlank(),
                    isError =
                        user.cpf.isNotBlank() &&
                                !isCpfValid
                )

                TextField(
                value = user.email,
                label = stringResource(
                    R.string.common_email
                ),
                onValueChange = viewModel::updateEmail,
                showCheck = isEmailValid && user.email.isNotBlank(),
                isError = user.email.isNotBlank() && !isEmailValid
            )

            TextField(
                value = user.confirmEmail,
                label = stringResource(
                    R.string.personal_data_confirm_email
                ),
                onValueChange = viewModel::updateConfirmEmail,
                showCheck = areEmailsEqual &&
                        user.confirmEmail.isNotBlank(),

                isError = user.confirmEmail.isNotBlank() &&
                        !areEmailsEqual
            )

            TextField(
                value = user.phone,
                label = stringResource(
                    R.string.personal_data_phone
                ),
                onValueChange = viewModel::updatePhone,
                inputType = InputType.NUMBERS_ONLY,
                showCheck = isPhoneValid &&
                        user.phone.isNotBlank(),

                isError = user.phone.isNotBlank() &&
                        !isPhoneValid
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    modifier = Modifier.size(20.dp),
                    checked = hasAcceptedTerms,
                    onCheckedChange = {
                        hasAcceptedTerms = it
                    },
                    colors = CheckboxDefaults.colors(
                        uncheckedColor = CheckboxBackground,
                        checkedColor = PrimaryBlue
                    )
                )

                Spacer(
                    modifier = Modifier.width(4.dp)
                )

                val annotatedText = buildAnnotatedString {

                    append(
                        stringResource(
                            R.string.personal_data_terms_prefix
                        )
                    )

                    append(" ")

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
                        append(
                            stringResource(
                                R.string.personal_data_terms_link
                            )
                        )

                    }

                    pop()
                }

                ClickableText(
                    text = annotatedText,
                    style = MaterialTheme.typography.labelMedium,
                    onClick = { offset ->

                        annotatedText
                            .getStringAnnotations(
                                tag = "TERMS",
                                start = offset,
                                end = offset
                            )
                            .firstOrNull()
                            ?.let {
                                showTerms = true
                            }
                    }
                )
            }

            }

            FixedBlueButton(
                text = stringResource(
                    R.string.common_continue
                ),
                enabled = isPersonalDataValid,
                onClick = {
                    navController.navigate(Routes.ADDRESS)
                }
            )
            if (showTerms) {
                TermsModal(
                    onFechar = {
                        showTerms = false
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PersonalDataScreenPreview() {
    PersonalDataScreen(navController = rememberNavController())
}
