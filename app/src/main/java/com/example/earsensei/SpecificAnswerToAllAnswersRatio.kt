package com.example.earsensei

import com.example.earsensei.dbmodels.QuizModel

class SpecificAnswerToAllAnswersRatio {
    fun calculateRatio(quizModels : ArrayList<QuizModel>, answer : String) : Float{
        val allAnswers : Float = quizModels.size.toFloat()
        val matchingAnswersRatio : Float = quizModels.filter { answer == it.userAnswer }.size.toFloat()
        return matchingAnswersRatio / allAnswers
    }
}