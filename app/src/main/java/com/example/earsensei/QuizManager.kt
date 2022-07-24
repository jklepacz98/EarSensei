package com.example.earsensei

import android.content.ContentValues
import android.widget.Toast
import com.example.earsensei.dbmodels.QuizRecordModel
import java.time.Duration
import java.util.*
import kotlin.collections.ArrayList

class QuizManager(
    val quizRecords: ArrayList<QuizRecordModel>,
    val quizType: String,
    val takeLast: Int,
    val earSenseiDBHelper: EarSenseiDBHelper
) {

    val quizList: Stack<QuizModel> = Stack()

    var currentQuiz : QuizModel? = null

    val questionPool : MutableList<String> = MusicTerminology.intervals.keys.toMutableList()

    fun generateQuizList(){

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
        val ratios = createCorrectAnswerMap().toList().sortedBy { it.second }.takeLast(numberOfRatios)
        return ratios
    }

    fun createCorrectAnswerMap(): MutableMap<String, Float> {
        val filteredQuizRecords =
            ArrayList<QuizRecordModel>(
                quizRecords
                    .filter { it.quizType == quizType }
                    .takeLast(takeLast))
        val correctAnswers = filteredQuizRecords.map { it.correctAnswer }.toSet()
        val correctAnswerMap = mutableMapOf<String, Float>()
        correctAnswers.forEach { correctAnswer ->
            val quizRecordsFilteredOnceAgain =
                ArrayList<QuizRecordModel>(filteredQuizRecords.filter { it.correctAnswer == correctAnswer })
            val ratio =
                QuizRatioCalculator.calculateCorrectAnswerRatio(quizRecordsFilteredOnceAgain)
            correctAnswerMap.put(correctAnswer, ratio)
        }
        return correctAnswerMap
    }
}