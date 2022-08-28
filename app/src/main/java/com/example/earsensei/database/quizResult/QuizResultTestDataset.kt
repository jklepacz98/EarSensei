package com.example.earsensei.database.quizResult

import com.example.earsensei.MusicTerminology
import com.example.earsensei.utils.QuizType

class QuizResultTestDataset {
    companion object {
        fun generateQuizResults(): List<QuizResult> {
            val quizResults = arrayListOf<QuizResult>()
            for (i in 0..10000) {
                quizResults.add(
                    QuizResult(
                        quizType = QuizType.INTERVALS,
                        baseNote = MusicTerminology.notesWithOctave.keys.random(),
                        correctAnswer = MusicTerminology.intervals.keys.random(),
                        userAnswer = MusicTerminology.intervals.keys.random(),
                        date = i
                    )
                )
            }
            return quizResults.toList()
        }

    }
}