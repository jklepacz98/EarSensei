package com.example.earsensei.graphdatapreparers

import com.example.earsensei.QuizRatioCalculator
import com.example.earsensei.dbmodels.QuizRecordModel

class DetailsGraphDataPreparer(val quizRecordModels : ArrayList<QuizRecordModel>, val xLabels : ArrayList<String>, val f : (QuizRecordModel) -> String, val filter : String)  {
    fun prepareXYHashMap() : LinkedHashMap<String, Float> {
        val ratioHashMap : LinkedHashMap<String, Float> = linkedMapOf()
        for(xLabel in xLabels){
            val filteredQuizRecordModels : ArrayList<QuizRecordModel> = ArrayList(quizRecordModels.filter { f(it) == xLabel })
            val xValue : Float = QuizRatioCalculator.calculateSpecificAnswerRatio(filteredQuizRecordModels, filter)
            ratioHashMap.put(xLabel, xValue)
        }
        return ratioHashMap
    }
}