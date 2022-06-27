package com.example.earsensei.graphdatapreparers

import com.example.earsensei.CorrectToAllAnswersRatio
import com.example.earsensei.dbmodels.QuizModel

class GraphDataPreparer(val quizModels : ArrayList<QuizModel>, val xLabels : ArrayList<String>, val f : (QuizModel) -> String)  {

    val correctToAllAnswersRatio : CorrectToAllAnswersRatio = CorrectToAllAnswersRatio()

    fun prepareXYHashMap() : LinkedHashMap<String, Float> {
        val ratioHashMap : LinkedHashMap<String, Float> = linkedMapOf()
        for(xLabel in xLabels){
            val filteredQuizModels : ArrayList<QuizModel> = ArrayList(quizModels.filter { f(it) == xLabel })
            val xValue : Float = correctToAllAnswersRatio.calculateRatio(filteredQuizModels)
            ratioHashMap.put(xLabel, xValue)
        }
        return ratioHashMap
    }
}