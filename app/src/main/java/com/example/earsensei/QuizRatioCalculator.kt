package com.example.earsensei

import com.example.earsensei.database.quizResult.QuizResult

class QuizRatioCalculator {
    companion object {
        fun calculateSpecificAnswerRatio(
            quizResults: List<QuizResult>,
            answer: String,
        ): Float {
            val allQuizResults: Float = quizResults.size.toFloat()
            val matchingAnswersRatio: Float =
                quizResults.filter { answer == it.userAnswer }.size.toFloat()
            return matchingAnswersRatio / allQuizResults
        }

        fun calculateCorrectAnswerRatio(quizResults: List<QuizResult>): Float {
            var allAnswers: Float = quizResults.size.toFloat()
            var correctAnswers: Float =
                quizResults.filter { it.userAnswer == it.correctAnswer }.size.toFloat()
            return correctAnswers / allAnswers
        }

    }

}