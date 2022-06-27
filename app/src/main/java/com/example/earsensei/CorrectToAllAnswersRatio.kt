package com.example.earsensei

import com.example.earsensei.dbmodels.QuizRecordModel

class CorrectToAllAnswersRatio {
    fun calculateRatio(quizRecordModels : ArrayList<QuizRecordModel>) : Float{
        var allAnswers : Float = quizRecordModels.size.toFloat()
        var correctAnswers : Float = quizRecordModels.filter { it.userAnswer == it.correctAnswer }.size.toFloat()
        return correctAnswers / allAnswers
    }
}