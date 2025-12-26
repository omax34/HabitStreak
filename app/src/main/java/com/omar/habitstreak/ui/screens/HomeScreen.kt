package com.omar.habitstreak.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.omar.habitstreak.data.Habit

// Definición de Colores
val DarkButton = Color(0xFF0F172A)
val LightBackground = Color(0xFFF8F9FA)
val FireOrange = Color(0xFFFF5722)

@Composable
fun HomeScreen(
    habits: List<Habit>,
    onAddHabit: (String) -> Unit,
    onDeleteHabit: (Habit) -> Unit,
    onCompleteHabit: (Habit) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBackground) // Fondo gris claro
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Título
            Text(
                text = "Mis Rachas",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 24.dp, top = 16.dp)
            )

            if (habits.isEmpty()) {
                // Mostrar estado Vacio
                EmptyState()
            } else {
                // Lista
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 100.dp) // Espacio para el botón de abajo
                ) {
                    items(habits) { habit ->
                        StreakCard(
                            habit = habit,
                            onComplete = { onCompleteHabit(habit) },
                            onDelete = { onDeleteHabit(habit) }
                        )
                    }
                }
            }
        }

        // Botton flotante inferior
        Button(
            onClick = { showDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = DarkButton),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
                .height(56.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Nueva racha", color = Color.White, fontSize = 16.sp)
        }

        if (showDialog) {
            AddHabitDialog(
                onDismiss = { showDialog = false },
                onConfirm = { name ->
                    onAddHabit(name)
                    showDialog = false
                }
            )
        }
    }
}

// Tarjeta de Racha
@Composable
fun StreakCard(habit: Habit, onComplete: () -> Unit, onDelete: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Nombre del hábito
                Text(
                    text = habit.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                // Eliminar
                IconButton(onClick = onDelete, modifier = Modifier.size(24.dp)) {
                    Icon(Icons.Outlined.Delete, contentDescription = "Borrar", tint = Color.Gray)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Contador
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🔥", fontSize = 20.sp, color = FireOrange) // Emoji de fuego o Icono
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${habit.streak} días",
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón "Marcar como completado"
            Button(
                onClick = onComplete,
                colors = ButtonDefaults.buttonColors(containerColor = DarkButton),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth().height(48.dp)
            ) {
                Text("Marcar como completado", color = Color.White)
            }
        }
    }
}

// Estado Vacío
@Composable
fun EmptyState() {
    Column(
        modifier = Modifier.fillMaxSize().padding(bottom = 100.dp), // Subirlo un poco
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFFE9ECEF), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("🎯", fontSize = 32.sp)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "No tienes rachas aún",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "Crea tu primera racha para empezar\na construir hábitos",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )
    }
}

// Diálogo de Agregar
@Composable
fun AddHabitDialog(onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Crear nueva racha",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    // La X de cerrar podría ir aquí si usas un IconButton
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    "¿Qué quieres hacer todos los días?",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text("Ej: Hacer ejercicio, Leer...") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancelar", color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { if (text.isNotBlank()) onConfirm(text) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray), // Gris según diseño hasta activar
                        enabled = text.isNotBlank(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Crear")
                    }
                }
            }
        }
    }
}