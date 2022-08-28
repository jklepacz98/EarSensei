package com.example.earsensei

import com.example.earsensei.database.quizResult.QuizResult
import java.util.*

class QuizManager(
    val quizResults: ArrayList<QuizResult>,
    val quizType: String,
    val takeLast: Int
) {

    val quizList: Stack<QuizModel> = Stack()
    var currentQuiz: QuizModel? = null
    val questionPool: MutableList<String> = MusicTerminology.intervals.keys.toMutableList()

    fun generateQuizList() {

    }


    fun takeWorstRecordsRatios(numberOfRatios: Int): List<Pair<String, Float>> {
        val ratios =
            createCorrectAnswerMap().toList().sortedBy { it.second }.takeLast(numberOfRatios)
        return ratios
    }

    fun createCorrectAnswerMap(): MutableMap<String, Float> {
        val filteredQuizResults =
            ArrayList<QuizResult>(
                quizResults
                    .filter { it.quizType == quizType }
                    .takeLast(takeLast))
        val correctAnswers = filteredQuizResults.map { it.correctAnswer }.toSet()
        val correctAnswerMap = mutableMapOf<String, Float>()
        correctAnswers.forEach { correctAnswer ->
            val quizRecordsFilteredOnceAgain =
                ArrayList<QuizResult>(filteredQuizResults.filter { it.correctAnswer == correctAnswer })
            val ratio =
                QuizRatioCalculator.calculateCorrectAnswerRatio(quizRecordsFilteredOnceAgain)
            correctAnswerMap.put(correctAnswer, ratio)
        }
        return correctAnswerMap
    }
}