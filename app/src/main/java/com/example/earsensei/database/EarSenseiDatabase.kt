package com.example.earsensei.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.earsensei.database.result.QuizResultDao
import com.example.earsensei.database.result.Result

@Database(entities = [Result::class], version = 1)
abstract class EarSenseiDatabase : RoomDatabase() {
    abstract fun userDao(): QuizResultDao

    companion object {
        @Volatile
        private var INSTANCE: EarSenseiDatabase? = null

        fun getDataBase(context: Context): EarSenseiDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EarSenseiDatabase::class.java,
                    "EarSenseiDB"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}