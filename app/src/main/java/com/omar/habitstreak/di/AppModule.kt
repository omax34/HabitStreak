package com.omar.habitstreak.di

import android.content.Context
import androidx.room.Room
import com.omar.habitstreak.data.AppDatabase
import com.omar.habitstreak.data.HabitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Crear la Base de Datos con Hilt
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "habit_streak_database" // Nombre del archivo en el celular
        ).build()
    }

    // Crear el DAO sacándolo de la base de datos
    @Provides
    @Singleton
    fun provideHabitDao(database: AppDatabase): HabitDao {
        return database.habitDao()
    }
}