package com.example.earsensei.database.quizResult

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizResultDao {
    @Query("SELECT * FROM quizResult")
    fun getLiveData(): LiveData<List<QuizResult>>

    @Query("SELECT * FROM quizResult")
    fun get(): List<QuizResult>

    @Query("SELECT * FROM quizresult WHERE :type = quizType AND :after > date")
    fun get(type: String, after: Long): List<QuizResult>

    @Query("SELECT COUNT(*) FROM quizresult WHERE isCorrect = 1")
    fun getCountAllCorrectResults(): Long

    @Query("SELECT COUNT(*) FROM quizresult WHERE :type = quizType AND :correctAnswer = correctAnswer")
    fun getCountAllByAnswer(type: String, correctAnswer: String): Long

    //todo zmienić nazwę argumentu
    @Query("SELECT COUNT(*) FROM quizresult WHERE :type = quizType AND :correctAnswer = correctAnswer AND isCorrect = 1")
    fun getCountAllCorrectResults(type: String, correctAnswer: String): Long

    @Query("SELECT COUNT(*) FROM quizresult WHERE :type = quizType AND :correctAnswer = correctAnswer AND :userAnswer = userAnswer")
    fun get(type: String, correctAnswer: String, userAnswer: String): Long

    @Query("SELECT userAnswer FROM quizresult WHERE :type = quizType AND :correctAnswer = correctAnswer AND isCorrect = 0")
    fun getAllUserAnswersWithoutCorrect(type: String, correctAnswer: String): List<String>

    @Insert
    fun insert(vararg quizResults: QuizResult): List<Long>

    @Query("DELETE FROM quizresult")
    suspend fun deleteAll()
}