package com.projeto.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freeapp.ui.theme.PrimaryWhite
import androidx.compose.foundation.lazy.items

@Composable
fun SelectionDropdown(
    itens: List<String>,
    onSelecionar: (String) -> Unit
) {

    Card(
        modifier = Modifier.padding(horizontal = 20.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = PrimaryWhite
        )
    ) {

        LazyColumn() {
            items(itens) { item ->
                Column {
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelecionar(item)
                            }
                            .padding(16.dp)
                    )

                    HorizontalDivider(
                        color = Color(0xFFE5E7EB)
                    )
                }
            }
        }
    }
}