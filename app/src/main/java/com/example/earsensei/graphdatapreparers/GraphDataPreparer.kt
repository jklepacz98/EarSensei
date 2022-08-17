package com.example.earsensei.graphdatapreparers

import com.example.earsensei.QuizRatioCalculator
import com.example.earsensei.database.result.Result

class GraphDataPreparer() {
    companion object {
        fun prepareIntervalsHashMap(
            results: ArrayList<Result>,
            xLabels: ArrayList<String>,
            f: (Result) -> String
        ): LinkedHashMap<String, Float> {
            val ratioHashMap: LinkedHashMap<String, Float> = linkedMapOf()
            for (xLabel in xLabels) {
                val filteredResults: ArrayList<Result> =
                    ArrayList(results.filter { f(it) == xLabel })
                val xValue: Float =
                    QuizRatioCalculator.calculateCorrectAnswerRatio(filteredResults)
                ratioHashMap.put(xLabel, xValue)
            }
            return ratioHashMap
        }

        fun prepareIntervalsHashMap(
            numberOfEntries: Int,
            results: ArrayList<Result>,
            xLabels: ArrayList<String>,
            f: (Result) -> String
        ): LinkedHashMap<String, Float> {
            val ratioHashMap: LinkedHashMap<String, Float> = linkedMapOf()
            val lastResults: ArrayList<Result> =
                ArrayList(results.takeLast(numberOfEntries))
            for (xLabel in xLabels) {
                val filteredResults: ArrayList<Result> = ArrayList(lastResults
                    .filter { f(it) == xLabel })
                var xValue: Float =
                    QuizRatioCalculator.calculateCorrectAnswerRatio(filteredResults)
                if (xValue.isNaN()) xValue = 0F
                ratioHashMap.put(xLabel, xValue)
            }
            return ratioHashMap
        }
    }

}