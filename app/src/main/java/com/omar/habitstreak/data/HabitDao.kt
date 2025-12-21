package com.omar.habitstreak.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HabitDao {

    @Query("SELECT * FROM habits ORDER BY createdDate DESC")
    //Se creo la funcion get AllHabits que retorna una lista. Flow para que esta lista se actualize/ leer datos en tiempo real
    fun getAllHabits(): Flow<List<Habit>>

    //Para insertar se usa REPLACE para crear/actualizar objetos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)
    //suspend para background

    @Delete
    suspend fun deleteHabit(habit: Habit)
}