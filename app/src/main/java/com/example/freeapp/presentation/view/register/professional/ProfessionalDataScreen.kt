package com.example.freeapp.presentation.view.register.professional

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freeapp.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.freeapp.presentation.components.BackButton
import com.example.freeapp.presentation.components.FixedBlueButton
import com.example.freeapp.presentation.components.InputType
import com.example.freeapp.presentation.components.TextField
import com.example.freeapp.presentation.navigation.Routes
import com.example.freeapp.presentation.viewmodel.ProfessionalDataViewModel

@Composable
fun ProfessionalDataScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: ProfessionalDataViewModel = ProfessionalDataViewModel()
){
    val user = viewModel.user

    val isProfessionalDataValid =
        user.profession.isNotBlank() &&
                user.specialty.isNotBlank() &&
                user.region.isNotBlank() &&
                user.schedule.isNotBlank()
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
                modifier = Modifier.height(4.dp)
            )

                Text(
                    text = stringResource(
                        R.string.professional_data_title
                    ),
                    style = MaterialTheme.typography.headlineLarge
                )

                TextField(
                    value = user.profession,
                    label = stringResource(
                        R.string.professional_data_profession
                    ),
                    onValueChange = viewModel::updateProfession,
                    inputType = InputType.LETTERS_ONLY
                )
                TextField(
                    value = user.specialty,
                    label = stringResource(
                        R.string.professional_data_specialty
                    ),
                    onValueChange = viewModel::updateSpecialty,
                    inputType = InputType.LETTERS_ONLY
                )
                TextField(
                    value = user.region,
                    label = stringResource(
                        R.string.professional_data_region
                    ),
                    onValueChange = viewModel::updateRegion,
                    inputType = InputType.LETTERS_ONLY
                )
                TextField(
                    value = user.schedule,
                    label = stringResource(
                        R.string.professional_data_schedule
                    ),
                    onValueChange = viewModel::updateSchedule
                )
            }

            FixedBlueButton(
                text = stringResource(
                    R.string.common_continue
                ),
                enabled = isProfessionalDataValid ,
                onClick = {
                    navController.navigate(Routes.BANK_DETAILS)
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfessionalDataScreenPreview() {
    ProfessionalDataScreen(navController = rememberNavController())
}