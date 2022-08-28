package com.example.earsensei.database.quizResult

import androidx.lifecycle.LiveData

class QuizResultRepository(private val quizResultDao: QuizResultDao) {
    val readAllData: LiveData<List<QuizResult>> = quizResultDao.getAllData()

    suspend fun addQuizResult(quizResult: QuizResult) {
        quizResultDao.insert(quizResult)
    }
}