package com.example.freeapp.presentation.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.freeapp.R
import com.example.freeapp.presentation.components.BottomNavigationBar
import com.example.freeapp.presentation.components.ProviderCard
import com.example.freeapp.presentation.components.ServiceCard
import com.example.freeapp.presentation.theme.PrimaryBlue
import com.example.freeapp.presentation.theme.PrimaryWhite
import com.example.freeapp.presentation.theme.fontColor
import com.example.freeapp.presentation.theme.neutreColor
import com.example.freeapp.presentation.viewmodel.AuthViewModel

@Composable
fun HomeScreen(
    viewModel: AuthViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val defaultUserName = stringResource(
        R.string.home_default_user
    )

    val userName = uiState.authenticatedUser.name
        .substringBefore(" ")
        .ifBlank {
            defaultUserName
        }

    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
                .fillMaxSize()
                .background(PrimaryWhite)
        ) {

            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = 20.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(
                            id = R.drawable.img_perfil
                        ),
                        contentDescription = stringResource(
                            R.string.home_profile_image_description
                        ),
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 12.dp)
                    ) {

                        Text(
                            text = stringResource(
                                R.string.home_greeting,
                                userName
                            ),
                            color = PrimaryBlue
                        )

                        Text(
                            text = stringResource(
                                R.string.home_finance_message
                            ),
                            color = fontColor
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(PrimaryBlue),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                painter = painterResource(
                                    id = R.drawable.ic_laucher_bell
                                ),
                                contentDescription = stringResource(
                                    R.string.home_notifications_description
                                ),
                                tint = PrimaryWhite
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(PrimaryBlue),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                painter = painterResource(
                                    id = R.drawable.fi_rr_search
                                ),
                                modifier = Modifier.size(15.dp),
                                contentDescription = stringResource(
                                    R.string.home_search_description
                                ),
                                tint = PrimaryWhite
                            )
                        }
                    }
                }
            }

            item {

                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(135.dp)
                            .align(Alignment.BottomStart),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = PrimaryBlue
                        )
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    start = 12.dp,
                                    top = 20.dp,
                                    bottom = 20.dp
                                ),
                            verticalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = stringResource(
                                    R.string.home_discount_title
                                ),
                                color = PrimaryWhite,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = stringResource(
                                    R.string.home_discount_subtitle
                                ),
                                color = PrimaryWhite
                            )
                        }
                    }

                    Image(
                        painter = painterResource(
                            id = R.drawable.img_banner
                        ),
                        contentDescription = stringResource(
                            R.string.home_discount_banner_description
                        ),
                        modifier = Modifier
                            .size(140.dp)
                            .align(Alignment.TopEnd)
                            .offset(
                                x = (-2).dp,
                                y = 0.dp
                            )
                    )
                }
            }

            item {

                Column {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 30.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = stringResource(
                                R.string.home_popular_services
                            ),
                            modifier = Modifier.weight(1f),
                            color = neutreColor,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = stringResource(
                                R.string.home_see_all
                            ),
                            color = PrimaryBlue,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        item {

                            ServiceCard(
                                title = stringResource(
                                    R.string.service_plumbing
                                ),
                                image = R.drawable.img_plumber
                            )
                        }

                        item {

                            ServiceCard(
                                title = stringResource(
                                    R.string.service_electric_work
                                ),
                                image = R.drawable.img_electric
                            )
                        }

                        item {

                            ServiceCard(
                                title = stringResource(
                                    R.string.service_solar
                                ),
                                image = R.drawable.img_solar
                            )
                        }
                    }
                }
            }

            item {

                Column {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 30.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = stringResource(
                                R.string.home_service_providers
                            ),
                            modifier = Modifier.weight(1f),
                            color = neutreColor,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = stringResource(
                                R.string.home_see_all
                            ),
                            color = PrimaryBlue,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        item {

                            ProviderCard(
                                name = stringResource(
                                    R.string.provider_maskot_kota
                                ),
                                profession = stringResource(
                                    R.string.provider_plumber
                                ),
                                rating = stringResource(
                                    R.string.provider_rating
                                ),
                                image = R.drawable.img_provider1,
                                imageBackgroundColor = Color(0xFFD4E5F8)
                            )
                        }

                        item {

                            ProviderCard(
                                name = stringResource(
                                    R.string.provider_shams_jan
                                ),
                                profession = stringResource(
                                    R.string.provider_electrician
                                ),
                                rating = stringResource(
                                    R.string.provider_rating
                                ),
                                image = R.drawable.img_provider2,
                                imageBackgroundColor = Color(0xFFE7B8E7)
                            )
                        }
                    }
                }
            }
        }
    }
}