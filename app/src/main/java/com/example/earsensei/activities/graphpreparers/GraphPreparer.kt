package com.example.earsensei.activities.graphpreparers

import com.example.earsensei.dbmodels.QuizRecordModel

class GraphPreparer(val quizRecordModels : ArrayList<QuizRecordModel>, val xLabels : ArrayList<String>, val f : (QuizRecordModel) -> String)  {


    fun prepareHashMap() : LinkedHashMap<String, Float> {
        val ratioHashMap : LinkedHashMap<String, Float> = linkedMapOf()
        for(xLabel in xLabels){
            val filteredQuizRecordModels : ArrayList<QuizRecordModel> = ArrayList(quizRecordModels.filter { f(it) == xLabel })
            val xValue : Float = correctAnswerRatio(filteredQuizRecordModels)
            ratioHashMap.put(xLabel, xValue)
        }
        return ratioHashMap
    }

    fun correctAnswerRatio(quizRecordModels : ArrayList<QuizRecordModel>) : Float{
        var allAnswers : Float = quizRecordModels.size.toFloat()
        var correctAnswers : Float = quizRecordModels.filter { it.userAnswer == it.correctAnswer }.size.toFloat()
        return correctAnswers / allAnswers
    }




}