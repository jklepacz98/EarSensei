package com.example.earsensei

import com.example.earsensei.dbmodels.QuizModel

class CorrectToAllAnswersRatio {
    fun calculateRatio(quizModels : ArrayList<QuizModel>) : Float{
        var allAnswers : Float = quizModels.size.toFloat()
        var correctAnswers : Float = quizModels.filter { it.userAnswer == it.correctAnswer }.size.toFloat()
        return correctAnswers / allAnswers
    }
}