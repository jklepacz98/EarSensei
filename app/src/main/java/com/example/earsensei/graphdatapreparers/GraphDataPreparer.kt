package com.example.earsensei.graphdatapreparers

import com.example.earsensei.QuizRatioCalculator
import com.example.earsensei.dbmodels.QuizModel

class GraphDataPreparer()  {
    companion object{
        fun prepareIntervalsHashMap(quizModels : ArrayList<QuizModel>, xLabels : ArrayList<String>, f : (QuizModel) -> String) : LinkedHashMap<String, Float> {
            val ratioHashMap : LinkedHashMap<String, Float> = linkedMapOf()
            for(xLabel in xLabels){
                val filteredQuizModels : ArrayList<QuizModel> = ArrayList(quizModels.filter { f(it) == xLabel })
                val xValue : Float = QuizRatioCalculator.calculateCorrectAnswerRatio(filteredQuizModels)
                ratioHashMap.put(xLabel, xValue)
            }
            return ratioHashMap
        }

        fun prepareIntervalsHashMap(numberOfEntries : Int, quizModels : ArrayList<QuizModel>, xLabels : ArrayList<String>, f : (QuizModel) -> String) : LinkedHashMap<String, Float> {
            val ratioHashMap : LinkedHashMap<String, Float> = linkedMapOf()
            val lastQuizModels : ArrayList<QuizModel> = ArrayList(quizModels.takeLast(numberOfEntries))
            for(xLabel in xLabels){
                val filteredQuizModels : ArrayList<QuizModel> = ArrayList(lastQuizModels
                    .filter { f(it) == xLabel })
                var xValue : Float = QuizRatioCalculator.calculateCorrectAnswerRatio(filteredQuizModels)
                if (xValue.isNaN()) xValue = 0F
                ratioHashMap.put(xLabel, xValue)
            }
            return ratioHashMap
        }
    }

}