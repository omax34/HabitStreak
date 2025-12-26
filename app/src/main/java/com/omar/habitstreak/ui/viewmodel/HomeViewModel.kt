package com.omar.habitstreak.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omar.habitstreak.data.Habit
import com.omar.habitstreak.data.HabitDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val habitDao: HabitDao // Inyeccion con Hilt
) : ViewModel() {

    // Esta variable observa la base de datos
    val habits: StateFlow<List<Habit>> = habitDao.getAllHabits()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Función para agregar un hábito
    fun addNewHabit(name: String) {
        viewModelScope.launch {
            habitDao.insertHabit(Habit(name = name))
        }
    }

    // Borrar hábito
    fun deleteHabit(habit: Habit) {
        viewModelScope.launch {
            habitDao.deleteHabit(habit)
        }
    }

    // Logica de racha
    fun completeHabit(habit: Habit) {
        viewModelScope.launch {
            val today = System.currentTimeMillis()
            // Aumentaremos la racha y actualizamos la fecha.
            val updatedHabit = habit.copy(
                streak = habit.streak + 1,
                lastCompletedDate = today
            )
            habitDao.updateHabit(updatedHabit)
        }
    }


}