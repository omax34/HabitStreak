package com.omar.habitstreak.data

import androidx.room.Database
import androidx.room.RoomDatabase

//Punto de acceso principal a la base de datos SQLite
//[Habit::class] respresenta las clases que pertenecen a la base de datos
//Trabajamos en la version 1
//ExportSchema = false por que por el momento no requerimos que se guarde un historial de cambios
@Database(entities = [Habit::class], version = 1, exportSchema = false)

//Heredamos de la libreria Room de Google
abstract class AppDatabase : RoomDatabase() {
    //Room implementara esta funcion que devuelve una instancia del DAO
    abstract fun habitDao(): HabitDao
}