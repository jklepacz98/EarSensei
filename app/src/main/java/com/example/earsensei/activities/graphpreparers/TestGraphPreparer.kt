package com.example.earsensei.activities.graphpreparers

import com.example.earsensei.dbmodels.TestModel
import kotlin.math.log

class TestGraphPreparer(val testModels : ArrayList<TestModel>, val xLabels : ArrayList<String>)  {


    fun prepareHashMap() : LinkedHashMap<String, Float> {
        val ratioHashMap : LinkedHashMap<String, Float> = linkedMapOf()
        for(xLabel in xLabels){
            //val filteredTestModels : ArrayList<TestModel> = ArrayList(testModels.filter { it.userAnswer == xLabel })
            //val xValue : Float = correctAnswerRatio(filteredTestModels)
            val xValue : Float = correctAnswerRatio(testModels)
            ratioHashMap.put(xLabel, xValue)
        }
        return ratioHashMap
    }

    fun correctAnswerRatio(testModels : ArrayList<TestModel>) : Float{
        var allAnswers : Float = testModels.size.toFloat()
        var correctAnswers : Float = testModels.filter { it.userAnswer == it.correctAnswer }.size.toFloat()
        return correctAnswers / allAnswers
    }


}