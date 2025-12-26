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
                val habits by viewModel.habits.collectAsState()

                HomeScreen(
                    habits = habits,
                    onAddHabit = { name -> viewModel.addNewHabit(name) },
                    onDeleteHabit = { habit -> viewModel.deleteHabit(habit) },
                    onCompleteHabit = { habit -> viewModel.completeHabit(habit) }
                )
            }
        }
    }
}