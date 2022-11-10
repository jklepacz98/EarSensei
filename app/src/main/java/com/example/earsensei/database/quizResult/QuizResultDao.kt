package com.example.earsensei.database.quizResult

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizResultDao {
    @Query("SELECT * FROM quizResult")
    fun getUserAnswer(): List<QuizResult>

    @Query("SELECT * FROM quizresult WHERE :type = quizType AND :after < date ORDER BY date LIMIT :limit")
    fun getQuizResult(type: String, after: Long, limit: Int): List<QuizResult>

    @Query("SELECT COUNT(*) FROM quizresult WHERE isCorrect = 1")
    fun getCount(): Long

    @Query("SELECT COUNT(*) FROM quizresult WHERE :type = quizType AND :correctAnswer = correctAnswer")
    fun getCount(type: String, correctAnswer: String): Long

    //todo zmienić nazwę argumentu
    @Query("SELECT COUNT(*) FROM quizresult WHERE :type = quizType AND :correctAnswer = correctAnswer AND isCorrect = 1")
    fun getCountCorrect(type: String, correctAnswer: String): Long

    @Query("SELECT COUNT(*) FROM quizresult WHERE :type = quizType AND :correctAnswer = correctAnswer AND :userAnswer = userAnswer")
    fun getCount(type: String, correctAnswer: String, userAnswer: String): Long

    @Query("SELECT userAnswer FROM quizresult WHERE :type = quizType AND :correctAnswer = correctAnswer AND isCorrect = 0")
    fun getUserAnswer(type: String, correctAnswer: String): List<String>

    @Insert
    fun insert(vararg quizResults: QuizResult): List<Long>

    @Query("DELETE FROM quizresult")
    suspend fun deleteAll()
}