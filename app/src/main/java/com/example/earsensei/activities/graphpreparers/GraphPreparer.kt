package com.example.earsensei.activities.graphpreparers

import com.example.earsensei.CorrectToAllAnswersRatio
import com.example.earsensei.dbmodels.QuizRecordModel

class GraphPreparer(val quizRecordModels : ArrayList<QuizRecordModel>, val xLabels : ArrayList<String>, val f : (QuizRecordModel) -> String)  {

    val correctToAllAnswersRatio : CorrectToAllAnswersRatio = CorrectToAllAnswersRatio()

    fun prepareHashMap() : LinkedHashMap<String, Float> {
        val ratioHashMap : LinkedHashMap<String, Float> = linkedMapOf()
        for(xLabel in xLabels){
            val filteredQuizRecordModels : ArrayList<QuizRecordModel> = ArrayList(quizRecordModels.filter { f(it) == xLabel })
            val xValue : Float = correctToAllAnswersRatio.calculateRatio(filteredQuizRecordModels)
            ratioHashMap.put(xLabel, xValue)
        }
        return ratioHashMap
    }
}