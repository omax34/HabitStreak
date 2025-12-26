package com.omar.habitstreak.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// tabla en la base de datos llamada "habits"
@Entity(tableName = "habits")
data class Habit(
    // ID único de cada fila
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    // Columnas de la tabla
    val name: String,
    val description: String = "",
    val streak: Int = 0,
    val lastCompletedDate: Long? = null //Feccha
)