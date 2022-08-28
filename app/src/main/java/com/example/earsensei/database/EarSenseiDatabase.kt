package com.example.earsensei.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.earsensei.database.progress.Progress
import com.example.earsensei.database.progress.ProgressDao
import com.example.earsensei.database.quizResult.QuizResult
import com.example.earsensei.database.quizResult.QuizResultDao

@Database(entities = [QuizResult::class, Progress::class], version = 1)
abstract class EarSenseiDatabase : RoomDatabase() {
    abstract fun resultDao(): QuizResultDao
    abstract fun progressDao(): ProgressDao

    companion object {
        @Volatile
        private var INSTANCE: EarSenseiDatabase? = null

        fun getDataBase(context: Context): EarSenseiDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            //todo what synchronized does?
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