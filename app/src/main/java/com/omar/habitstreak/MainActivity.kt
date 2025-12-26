package com.omar.habitstreak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omar.habitstreak.ui.screens.AddHabitScreen
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
                // Controlador de navegación
                val navController = rememberNavController()

                // Mapa de navegacion
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    //Pantalla de Inicio
                    composable("home") {
                        // Observamos la lista de hábitos del ViewModel
                        // "by" convierte el Flow en una lista que se actualiza sola
                        // viewModel.habits es un FLOW
                        val habits by viewModel.habits.collectAsState()
                        HomeScreen(
                            habits = habits,
                            onAddHabit = {
                                // Cuando tocan el botón +, navegamos a la pantalla de agregar
                                navController.navigate("add_habit")
                            }
                        )
                    }

                    //Pantalla de Agregar
                    composable("add_habit") {
                        AddHabitScreen(
                            onBack = { navController.popBackStack() },
                            onSave = { name, description ->
                                // Guardamos el dato
                                viewModel.addNewHabit(name, description)
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}