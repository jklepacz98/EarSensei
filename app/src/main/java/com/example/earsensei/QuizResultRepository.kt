package com.example.earsensei

import androidx.lifecycle.LiveData
import com.example.earsensei.database.result.QuizResultDao
import com.example.earsensei.database.result.Result

class QuizResultRepository(private val quizResultDao: QuizResultDao) {
    val readAllData: LiveData<List<Result>> = quizResultDao.readAllData()
    suspend fun addQuizResult(result: Result) {
        quizResultDao.insert(result)
    }
}