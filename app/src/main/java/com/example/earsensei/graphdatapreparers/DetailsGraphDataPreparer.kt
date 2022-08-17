package com.example.earsensei.graphdatapreparers

import com.example.earsensei.QuizRatioCalculator
import com.example.earsensei.database.result.Result

class DetailsGraphDataPreparer(
    val results: ArrayList<Result>,
    val xLabels: ArrayList<String>,
    val f: (Result) -> String,
    val filter: String
) {
    fun prepareXYHashMap(): LinkedHashMap<String, Float> {
        val ratioHashMap: LinkedHashMap<String, Float> = linkedMapOf()
        for (xLabel in xLabels) {
            val filteredResults: ArrayList<Result> =
                ArrayList(results.filter { f(it) == xLabel })
            val xValue: Float =
                QuizRatioCalculator.calculateSpecificAnswerRatio(filteredResults, filter)
            ratioHashMap.put(xLabel, xValue)
        }
        return ratioHashMap
    }
}