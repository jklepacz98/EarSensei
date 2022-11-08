package com.example.earsensei.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.earsensei.database.quizResult.QuizResult
import com.example.earsensei.database.quizResult.QuizResultDao
import com.example.earsensei.database.unlockedQuestion.UnlockedQuestion
import com.example.earsensei.database.unlockedQuestion.UnlockedQuestionDao

@Database(entities = [QuizResult::class, UnlockedQuestion::class], version = 1)
abstract class EarSenseiDatabase : RoomDatabase() {
    abstract fun resultDao(): QuizResultDao
    abstract fun unlockedQuestionDao(): UnlockedQuestionDao

    companion object {
        const val DATABASE_NAME = "EarSenseiDB"
    }
}