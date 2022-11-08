package com.example.earsensei

import com.example.earsensei.database.EarSenseiDatabase

class ProgressManager(private val db: EarSenseiDatabase) {
    fun getWorstRecord(type: String): Map.Entry<String, Float> {
        val progressions = db.unlockedQuestionDao().getByType(type)
        val progressionsRatios = mutableMapOf<String, Float>()
        progressions.forEach {
            val dividend = db.resultDao().getCountAllCorrectResults(it.type, it.question)
            val divider = db.resultDao().getCountAllByAnswer(it.type, it.question)
            val result = dividend.toFloat() / divider.toFloat()
            progressionsRatios.put(it.question, result)
        }
        val worst = progressionsRatios.minByOrNull { it.value }
        return worst!!
    }

    fun getMostCommonMistake(type: String, correctAnswer: String): Map.Entry<String, Long> {
        val progressions = db.unlockedQuestionDao().getByType(type).map { it.question }
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