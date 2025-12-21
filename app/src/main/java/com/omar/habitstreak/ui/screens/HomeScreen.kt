package com.omar.habitstreak.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {
    // Esquema de material design (TopBar, FAB, Content)
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        // Box para centrar contenido
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Habitos")
        }
    }
}