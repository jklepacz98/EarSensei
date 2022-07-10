package com.example.earsensei

import com.example.earsensei.dbmodels.QuizRecordModel

class QuizRatioCalculator {
    companion object{
        fun calculateSpecificAnswerRatio(quizRecordModels : ArrayList<QuizRecordModel>, answer : String) : Float{
            val allAnswers : Float = quizRecordModels.size.toFloat()
            val matchingAnswersRatio : Float = quizRecordModels.filter { answer == it.userAnswer }.size.toFloat()
            return matchingAnswersRatio / allAnswers
        }
        fun calculateCorrectAnswerRatio(quizRecordModels : ArrayList<QuizRecordModel>) : Float{
            var allAnswers : Float = quizRecordModels.size.toFloat()
            var correctAnswers : Float = quizRecordModels.filter { it.userAnswer == it.correctAnswer }.size.toFloat()
            return correctAnswers / allAnswers
        }

    }

}