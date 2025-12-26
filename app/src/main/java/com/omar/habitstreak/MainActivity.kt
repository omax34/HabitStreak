package com.omar.habitstreak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.omar.habitstreak.ui.screens.HomeScreen
import com.omar.habitstreak.ui.theme.HabitStreakTheme
import com.omar.habitstreak.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint// Hacemos uso de Hilt
class MainActivity : ComponentActivity() {

    // Inyectamos el ViewModel
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabitStreakTheme {
                // Observamos la lista de hábitos del ViewModel
                // "by" convierte el Flow en una lista que se actualiza sola
                // viewModel.habits es un FLOW
                val habi by viewModel.habits.collectAsState()

                // Llamamos a la pantalla pasándole los datos y la acción
                HomeScreen(
                    habits = habi, onAddHabit = { viewModel.addTestHabit() }
                )
            }
        }
    }
}