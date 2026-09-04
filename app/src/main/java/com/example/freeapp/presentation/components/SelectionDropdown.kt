package com.example.freeapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.freeapp.presentation.theme.PrimaryWhite
import androidx.compose.foundation.lazy.items

@Composable
fun SelectionDropdown(
    items: List<String>,
    onSelect: (String) -> Unit
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

        LazyColumn {
            items(items) { item ->
                Column {
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onSelect(item)
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