package com.example.earsensei.graphdatapreparers

import com.example.earsensei.SpecificAnswerToAllAnswersRatio
import com.example.earsensei.dbmodels.QuizModel

class DetailsGraphDataPreparer(val quizModels : ArrayList<QuizModel>, val xLabels : ArrayList<String>, val f : (QuizModel) -> String, val filter : String)  {

    val specificAnswerToAllAnswersRatio : SpecificAnswerToAllAnswersRatio = SpecificAnswerToAllAnswersRatio()

    fun prepareXYHashMap() : LinkedHashMap<String, Float> {
        val ratioHashMap : LinkedHashMap<String, Float> = linkedMapOf()
        for(xLabel in xLabels){
            val filteredQuizModels : ArrayList<QuizModel> = ArrayList(quizModels.filter { f(it) == xLabel })
            val xValue : Float = specificAnswerToAllAnswersRatio.calculateRatio(filteredQuizModels, filter)
            ratioHashMap.put(xLabel, xValue)
        }
        return ratioHashMap
    }
}