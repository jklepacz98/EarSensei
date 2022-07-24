package com.example.earsensei.graphdatapreparers

import com.example.earsensei.QuizRatioCalculator
import com.example.earsensei.dbmodels.QuizRecordModel

class GraphDataPreparer()  {
    companion object{
        fun prepareIntervalsHashMap(quizRecordModels : ArrayList<QuizRecordModel>, xLabels : ArrayList<String>, f : (QuizRecordModel) -> String) : LinkedHashMap<String, Float> {
            val ratioHashMap : LinkedHashMap<String, Float> = linkedMapOf()
            for(xLabel in xLabels){
                val filteredQuizRecordModels : ArrayList<QuizRecordModel> = ArrayList(quizRecordModels.filter { f(it) == xLabel })
                val xValue : Float = QuizRatioCalculator.calculateCorrectAnswerRatio(filteredQuizRecordModels)
                ratioHashMap.put(xLabel, xValue)
            }
            return ratioHashMap
        }

        fun prepareIntervalsHashMap(numberOfEntries : Int, quizRecordModels : ArrayList<QuizRecordModel>, xLabels : ArrayList<String>, f : (QuizRecordModel) -> String) : LinkedHashMap<String, Float> {
            val ratioHashMap : LinkedHashMap<String, Float> = linkedMapOf()
            val lastQuizRecordModels : ArrayList<QuizRecordModel> = ArrayList(quizRecordModels.takeLast(numberOfEntries))
            for(xLabel in xLabels){
                val filteredQuizRecordModels : ArrayList<QuizRecordModel> = ArrayList(lastQuizRecordModels
                    .filter { f(it) == xLabel })
                var xValue : Float = QuizRatioCalculator.calculateCorrectAnswerRatio(filteredQuizRecordModels)
                if (xValue.isNaN()) xValue = 0F
                ratioHashMap.put(xLabel, xValue)
            }
            return ratioHashMap
        }
    }

}