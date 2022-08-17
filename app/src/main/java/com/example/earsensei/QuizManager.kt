package com.example.earsensei

import com.example.earsensei.database.result.Result
import java.util.*

class QuizManager(
    val results: ArrayList<Result>,
    val quizType: String,
    val takeLast: Int
) {

    val quizList: Stack<QuizModel> = Stack()

    var currentQuiz: QuizModel? = null

    val questionPool: MutableList<String> = MusicTerminology.intervals.keys.toMutableList()

    fun generateQuizList() {

    }

    //todo
//    fun checkAnswer(userAnswer : String){
//        val date: Long = Date().time
//        val contentValues: ContentValues = earSenseiDBHelper.createIntervalsContentValues(
//            currentQuiz.baseNote,
//            quizType,
//            currentQuiz.correctAnswer,
//            userAnswer,
//            date
//        )
//        if(userAnswer == currentQuiz.correctAnswer) {
//
//        }
//    }

    fun takeWorstRecordsRatios(numberOfRatios: Int): List<Pair<String, Float>> {
        val ratios =
            createCorrectAnswerMap().toList().sortedBy { it.second }.takeLast(numberOfRatios)
        return ratios
    }

    fun createCorrectAnswerMap(): MutableMap<String, Float> {
        val filteredResults =
            ArrayList<Result>(
                results
                    .filter { it.quizType == quizType }
                    .takeLast(takeLast))
        val correctAnswers = filteredResults.map { it.correctAnswer }.toSet()
        val correctAnswerMap = mutableMapOf<String, Float>()
        correctAnswers.forEach { correctAnswer ->
            val quizRecordsFilteredOnceAgain =
                ArrayList<Result>(filteredResults.filter { it.correctAnswer == correctAnswer })
            val ratio =
                QuizRatioCalculator.calculateCorrectAnswerRatio(quizRecordsFilteredOnceAgain)
            correctAnswerMap.put(correctAnswer, ratio)
        }
        return correctAnswerMap
    }
}