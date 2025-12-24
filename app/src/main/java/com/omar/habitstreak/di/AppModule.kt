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
@InstallIn(SingletonComponent::class) // Este módulo vive toda la vida de la app
object AppModule {

    // 1. Enseña a Hilt cómo crear la Base de Datos
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "habit_streak_database" // Nombre del archivo en el celular
        ).build()
    }

    // 2. Enseña a Hilt cómo crear el DAO (sacándolo de la base de datos)
    @Provides
    @Singleton
    fun provideHabitDao(database: AppDatabase): HabitDao {
        return database.habitDao()
    }
}