package com.example.earsensei.activities.graphpreparers

import com.example.earsensei.dbmodels.IntervalModel
import kotlin.math.log

class GraphPreparer(val intervalModels : ArrayList<IntervalModel>, val xLabels : ArrayList<String>)  {


    fun prepareHashMap() : LinkedHashMap<String, Float> {
        val ratioHashMap : LinkedHashMap<String, Float> = linkedMapOf()
        for(xLabel in xLabels){
            //val filteredIntervalModels : ArrayList<IntervalModel> = ArrayList(intervalModels.filter { it.userAnswer == xLabel })
            //val xValue : Float = correctAnswerRatio(filteredIntervalModels)
            val xValue : Float = correctAnswerRatio(intervalModels)
            ratioHashMap.put(xLabel, xValue)
        }
        return ratioHashMap
    }

    fun correctAnswerRatio(intervalModels : ArrayList<IntervalModel>) : Float{
        var allAnswers : Float = intervalModels.size.toFloat()
        var correctAnswers : Float = intervalModels.filter { it.userAnswer == it.correctAnswer }.size.toFloat()
        return correctAnswers / allAnswers
    }


}