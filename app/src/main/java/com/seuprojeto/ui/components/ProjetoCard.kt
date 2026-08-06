package com.seuprojeto.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seuprojeto.domain.model.Projeto

@Composable
fun ProjetoCard(
    projeto: Projeto,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = projeto.nome,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = projeto.descricao,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}