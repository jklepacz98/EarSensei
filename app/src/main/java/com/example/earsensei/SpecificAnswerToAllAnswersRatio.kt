package com.example.earsensei

import com.example.earsensei.dbmodels.QuizRecordModel

class SpecificAnswerToAllAnswersRatio {
    fun calculateRatio(quizRecordModels : ArrayList<QuizRecordModel>, answer : String) : Float{
        val allAnswers : Float = quizRecordModels.size.toFloat()
        val matchingAnswersRatio : Float = quizRecordModels.filter { answer == it.userAnswer }.size.toFloat()
        return matchingAnswersRatio / allAnswers
    }
}