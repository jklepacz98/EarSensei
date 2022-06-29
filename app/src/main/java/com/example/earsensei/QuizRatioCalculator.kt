package com.example.earsensei

import com.example.earsensei.dbmodels.QuizModel

class QuizRatioCalculator {
    companion object{
        fun calculateSpecificAnswerRatio(quizModels : ArrayList<QuizModel>, answer : String) : Float{
            val allAnswers : Float = quizModels.size.toFloat()
            val matchingAnswersRatio : Float = quizModels.filter { answer == it.userAnswer }.size.toFloat()
            return matchingAnswersRatio / allAnswers
        }
        fun calculateCorrectAnswerRatio(quizModels : ArrayList<QuizModel>) : Float{
            var allAnswers : Float = quizModels.size.toFloat()
            var correctAnswers : Float = quizModels.filter { it.userAnswer == it.correctAnswer }.size.toFloat()
            return correctAnswers / allAnswers
        }

    }

}