package com.example.earsensei.graphdatapreparers

import android.util.Log
import com.example.earsensei.QuizRatioCalculator
import com.example.earsensei.database.quizResult.QuizResult

class GraphDataPreparer() {
    companion object {
        fun prepareIntervalsMap(
            quizResults: List<QuizResult>,
            xLabels: List<String>
        ): LinkedHashMap<String, Float> {
            val ratioHashMap: LinkedHashMap<String, Float> = linkedMapOf()
            xLabels.forEach { xLabel ->
                val xValue: Float = QuizRatioCalculator.calculateCorrectAnswerRatio(
                    quizResults.filter { it.correctAnswer == xLabel })
                ratioHashMap.put(xLabel, xValue)
            }
            Log.d("cos1", ratioHashMap.toString())
            return ratioHashMap
        }
    }
}