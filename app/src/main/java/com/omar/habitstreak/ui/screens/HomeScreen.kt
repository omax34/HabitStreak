package com.omar.habitstreak.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.omar.habitstreak.data.Habit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    habits: List<Habit>,          // Recibe la lista de datos
    onAddHabit: () -> Unit        // Recibe la función para agregar
) {
    Scaffold(
        topBar = {
            //Barra de titulo
            TopAppBar(
                title = { Text("Mis Hábitos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        // Boton inferior derecho
        floatingActionButton = {
            FloatingActionButton(onClick = onAddHabit) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Hábito")
            }
        }
    ) { paddingValues ->
        // Contenido
        Box(
            modifier = Modifier
                // Llena espacio horizontal
                .fillMaxSize()
                // Scaffold calcula cuánto espacio ocupa la TopBar
                .padding(paddingValues)
        ) {
            if (habits.isEmpty()) {
                // Estado vacío
                Text(
                    text = "No hay hábitos aún. ¡Agrega uno!",
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                // Lista de Hábitos
                // Lazy dibujara solo los que caben en pantalla para ahorrar recursos
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    // Por cada item/habito existente dibujaremos una seccion con la funcion HabitITem
                    items(habits) { habit ->
                        HabitItem(habit)
                    }
                }
            }
        }
    }
}

@Composable
fun HabitItem(habit: Habit) {
    // Diseño de la tarjeta para cada item
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = habit.name,
                style = MaterialTheme.typography.titleMedium
            )
            if (habit.description.isNotEmpty()) {
                Text(
                    text = habit.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}