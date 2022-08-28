package com.example.earsensei.database.quizResult

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizResultDao {
    @Query("SELECT * FROM quizResult")
    fun getAllData(): LiveData<List<QuizResult>>

    @Query("SELECT * FROM quizResult")
    fun getAllData2(): List<QuizResult>

    //todo zmienić nazwę argumentu
    @Query("SELECT COUNT(*) FROM quizresult WHERE :answer = correctAnswer AND isCorrect = 1")
    fun getAllCorrectResults(answer: String): Long

    @Query("SELECT COUNT(*) FROM quizresult WHERE isCorrect = 1")
    fun getAllCorrectResults(): Long

    @Insert()
    suspend fun insert(vararg quizResults: QuizResult): List<Long>
}