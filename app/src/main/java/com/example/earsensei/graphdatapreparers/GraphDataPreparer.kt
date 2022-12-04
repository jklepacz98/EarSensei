package com.example.earsensei.graphdatapreparers

import com.example.earsensei.QuizRatioCalculator
import com.example.earsensei.database.quizResult.QuizResult

class GraphDataPreparer() {
    companion object {
        fun prepareIntervalsMap(
            quizResults: List<QuizResult>,
            xLabels: List<String>,
        ): LinkedHashMap<String, Float> {
            val ratioHashMap: LinkedHashMap<String, Float> = linkedMapOf()
            xLabels.forEach { xLabel ->
                val xValue: Float = QuizRatioCalculator.calculateCorrectAnswerRatio(
                    quizResults.filter { it.correctAnswer == xLabel })
                ratioHashMap[xLabel] = xValue
            }
            return ratioHashMap
        }
    }
}