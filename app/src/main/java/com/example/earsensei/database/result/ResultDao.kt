package com.example.earsensei.database.result

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizResultDao {
    @Query("SELECT * FROM result")
    fun readAllData(): LiveData<List<Result>>

//    @Query("SELECT * FROM quizResult WHERE id IN (:quizResultIds)")
//    fun loadAllByIds(quizResultIds: IntArray): List<QuizResult>

    //todo co robi OnConflictStrategy
    @Insert()
    suspend fun insert(vararg results: Result)

//    @Delete
//    fun delete(quizResult: QuizResult)
}