package com.seuprojeto.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seuprojeto.di.GuiaModule
import com.seuprojeto.repository.ProjetoRepository
import com.seuprojeto.ui.components.ProjetoCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    repository: ProjetoRepository = GuiaModule.projetoRepository
) {
    val projeto = repository.buscarProjetoInicial()

    Surface(modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            ProjetoCard(projeto = projeto)
        }
    }
}