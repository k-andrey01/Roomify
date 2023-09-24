package com.example.myroom.database

import android.content.Context
import android.util.Log
import androidx.room.Room

object DatabaseManager {
    private var instance: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        if (instance == null) {
            synchronized(AppDatabase::class) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
            }
        }
        return instance!!
    }
}
