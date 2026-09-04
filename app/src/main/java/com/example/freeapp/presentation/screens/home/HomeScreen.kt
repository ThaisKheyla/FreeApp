package com.example.freeapp.presentation.screens.home

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.example.freeapp.presentation.viewmodel.UsuarioViewModel

@Composable
fun HomeScreen(
    viewModel: UsuarioViewModel
) {
    val userName = viewModel.usuario.nome
        .substringBefore(" ")
        .ifBlank { "usuário" }

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
                        contentDescription = "Imagem de Perfil",
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
                            text = "Hello $userName",
                            color = PrimaryBlue
                        )

                        Text(
                            text = "Your finances are looking good",
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
                                contentDescription = "Notificações",
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
                                contentDescription = "Pesquisar",
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
                                text = "Get 30% off",
                                color = PrimaryWhite,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = "Just by Booking Home Services",
                                color = PrimaryWhite
                            )
                        }
                    }

                    Image(
                        painter = painterResource(
                            id = R.drawable.img_banner
                        ),
                        contentDescription = "Banner",

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
                            text = "Popular Services",
                            modifier = Modifier.weight(1f),
                            color = neutreColor,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Ver tudo",
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
                                title = "Plumbing",
                                image = R.drawable.img_plumber
                            )
                        }

                        item {
                            ServiceCard(
                                title = "Electric work",
                                image = R.drawable.img_electric
                            )
                        }

                        item {
                            ServiceCard(
                                title = "Solar",
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
                            text = "Service Providers",
                            modifier = Modifier.weight(1f),
                            color = neutreColor,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Ver tudo",
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
                                name = "Maskot Kota",
                                profession = "Plumber",
                                rating = "4.8",
                                image = R.drawable.img_provider1,
                                imageBackgroundColor = Color(0xFFD4E5F8)
                            )
                        }

                        item {
                            ProviderCard(
                                name = "Shams Jan",
                                profession = "Electrician",
                                rating = "4.8",
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