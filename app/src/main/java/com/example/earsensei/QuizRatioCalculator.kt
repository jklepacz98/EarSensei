package com.example.earsensei

import com.example.earsensei.database.result.Result

class QuizRatioCalculator {
    companion object {
        fun calculateSpecificAnswerRatio(
            results: ArrayList<Result>,
            answer: String
        ): Float {
            val allAnswers: Float = results.size.toFloat()
            val matchingAnswersRatio: Float =
                results.filter { answer == it.userAnswer }.size.toFloat()
            return matchingAnswersRatio / allAnswers
        }

        fun calculateCorrectAnswerRatio(results: ArrayList<Result>): Float {
            var allAnswers: Float = results.size.toFloat()
            var correctAnswers: Float =
                results.filter { it.userAnswer == it.correctAnswer }.size.toFloat()
            return correctAnswers / allAnswers
        }

    }

}