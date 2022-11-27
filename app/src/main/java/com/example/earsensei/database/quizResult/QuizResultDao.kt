package com.example.earsensei.database.quizResult

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizResultDao {
    @Query("SELECT * FROM quizResult")
    fun getUserAnswer(): List<QuizResult>

    @Query("SELECT COUNT(*) FROM quizResult WHERE :type = type AND :correctAnswer = correctAnswer")
    fun getCount(type: String, correctAnswer: String): Long

    //todo zmienić nazwę argumentu
    @Query("SELECT COUNT(*) FROM quizResult WHERE :type = type AND :correctAnswer = correctAnswer AND isCorrect = 1")
    fun getCountCorrect(type: String, correctAnswer: String): Long

    @Query("SELECT COUNT(*) FROM quizResult WHERE :type = type AND :correctAnswer = correctAnswer AND :userAnswer = userAnswer")
    fun getCount(type: String, correctAnswer: String, userAnswer: String): Long

    @Query("SELECT userAnswer FROM quizResult WHERE :type = type AND :correctAnswer = correctAnswer AND isCorrect = 0")
    fun getUserAnswer(type: String, correctAnswer: String): List<String>

    @Insert
    fun insert(vararg quizResults: QuizResult): List<Long>

    @Query("DELETE FROM quizResult")
    suspend fun deleteAll()
}