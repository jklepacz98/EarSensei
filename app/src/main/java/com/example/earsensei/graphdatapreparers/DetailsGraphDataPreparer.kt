package com.example.earsensei.graphdatapreparers

import com.example.earsensei.QuizRatioCalculator
import com.example.earsensei.dbmodels.QuizModel

class DetailsGraphDataPreparer(val quizModels : ArrayList<QuizModel>, val xLabels : ArrayList<String>, val f : (QuizModel) -> String, val filter : String)  {
    fun prepareXYHashMap() : LinkedHashMap<String, Float> {
        val ratioHashMap : LinkedHashMap<String, Float> = linkedMapOf()
        for(xLabel in xLabels){
            val filteredQuizModels : ArrayList<QuizModel> = ArrayList(quizModels.filter { f(it) == xLabel })
            val xValue : Float = QuizRatioCalculator.calculateSpecificAnswerRatio(filteredQuizModels, filter)
            ratioHashMap.put(xLabel, xValue)
        }
        return ratioHashMap
    }
}