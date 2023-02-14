package com.example.earsensei.database.quizResult

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizResultDao {
    @Query("SELECT * FROM quizResult")
    suspend fun getUserAnswer(): List<QuizResult>

    @Query("SELECT * FROM quizResult WHERE :type = type")
    suspend fun getUserAnswer(type: String): List<QuizResult>

    @Query("SELECT userAnswer FROM quizResult WHERE :type = type AND :correctAnswer = correctAnswer AND isCorrect = 0")
    suspend fun getUserAnswer(type: String, correctAnswer: String): List<String>

    @Query("SELECT COUNT(*) FROM quizResult WHERE :type = type AND :correctAnswer = correctAnswer")
    suspend fun getCount(type: String, correctAnswer: String): Long

    //todo zmienić nazwę argumentu
    @Query("SELECT COUNT(*) FROM quizResult WHERE :type = type AND :correctAnswer = correctAnswer AND isCorrect = 1")
    suspend fun getCountCorrect(type: String, correctAnswer: String): Long

    @Query("SELECT COUNT(*) FROM quizResult WHERE :type = type AND :correctAnswer = correctAnswer AND :userAnswer = userAnswer")
    suspend fun getCount(type: String, correctAnswer: String, userAnswer: String): Long

    @Insert
    suspend fun insert(vararg quizResults: QuizResult): List<Long>

    @Query("DELETE FROM quizResult")
    suspend fun deleteAll()
}