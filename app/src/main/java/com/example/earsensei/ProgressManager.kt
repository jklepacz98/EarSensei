package com.example.earsensei

import com.example.earsensei.database.EarSenseiDatabase

class ProgressManager(private val db: EarSenseiDatabase) {
    suspend fun getWorstRecord(type: String): Map.Entry<String, Float> {
        val progressions = db.unlockedQuestionDao().getByType(type)
        val progressionsRatios = mutableMapOf<String, Float>()
        progressions.forEach {
            val dividend = db.resultDao().getCountCorrect(it.type, it.name)
            val divider = db.resultDao().getCount(it.type, it.name)
            val result = dividend.toFloat() / divider.toFloat()
            progressionsRatios.put(it.name, result)
        }
        val worst = progressionsRatios.minByOrNull { it.value }
        return worst!!
    }

    suspend fun getMostCommonMistake(type: String, correctAnswer: String): Map.Entry<String, Long> {
        val progressions = db.unlockedQuestionDao().getByType(type).map { it.name }
        val userAnswer = db.resultDao().getUserAnswer(type, correctAnswer)
            .filter { userAnswer -> progressions.contains(userAnswer) }
        val map = mutableMapOf<String, Long>()
        userAnswer.forEach {
            val count = db.resultDao().getCount(type, it, correctAnswer)
            map.put(it, count)
        }
        return map.maxByOrNull { it.value }!!
    }
}