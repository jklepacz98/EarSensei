package com.example.earsensei

import android.util.Log
import com.example.earsensei.database.EarSenseiDatabase
import java.util.*

class ProgressManager(private val db: EarSenseiDatabase) {
    suspend fun checkLastAnswers(type: String) {
        val latestDate = db.progressionDao().getLatest(type)
        Log.d("cos1", Date(latestDate).toString())
    }

    fun getWorstRecord(type: String): Map.Entry<String, Float> {
        val progressions = db.progressionDao().getByType(type)
        Log.d("cos22", progressions.toString())
        val progressionsRatios = mutableMapOf<String, Float>()
        progressions.forEach {
            Log.d("cos1", it.question.toString())
            val dividend = db.resultDao().getCountAllCorrectResults(it.type, it.question)
            Log.d("cos2", dividend.toString())
            val divider = db.resultDao().getCountAllByAnswer(it.type, it.question)
            Log.d("cos3", divider.toString())
            val result = dividend.toFloat() / divider.toFloat()
            Log.d("cos4", result.toString())
            progressionsRatios.put(it.question, result)
        }
        val worst = progressionsRatios.minByOrNull { it.value }
        Log.d("cos1", worst.toString())
        return worst!!
    }

    fun getMostCommonMistake(type: String, correctAnswer: String): Map.Entry<String, Long> {
        val progressions = db.progressionDao().getByType(type).map { it.question }
        val userAnswer = db.resultDao().getAllUserAnswersWithoutCorrect(type, correctAnswer)
            .filter { userAnswer -> progressions.contains(userAnswer) }
        val map = mutableMapOf<String, Long>()
        userAnswer.forEach {
            val count = db.resultDao().get(type, it, correctAnswer)
            map.put(it, count)
        }
        return map.maxByOrNull { it.value }!!
    }
}