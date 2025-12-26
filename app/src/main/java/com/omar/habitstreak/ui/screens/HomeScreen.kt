package com.omar.habitstreak.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omar.habitstreak.data.Habit
//Componentes
import com.omar.habitstreak.ui.components.AddHabitDialog
import com.omar.habitstreak.ui.components.EmptyState
import com.omar.habitstreak.ui.components.StreakCard

val DarkButton = Color(0xFF0F172A)
val LightBackground = Color(0xFFF8F9FA)

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
            .background(LightBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Mis Rachas",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 24.dp, top = 16.dp)
            )

            if (habits.isEmpty()) {
                EmptyState()
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding = PaddingValues(bottom = 100.dp)
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