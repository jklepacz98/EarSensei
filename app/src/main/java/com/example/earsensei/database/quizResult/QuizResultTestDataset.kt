package com.example.earsensei.database.quizResult

import com.example.earsensei.INTERVALS
import com.example.earsensei.database.progression.Progression
import com.example.earsensei.notesWithOctave
import com.example.earsensei.utils.QuizType
import com.example.earsensei.utils.randomEnum

class QuizResultTestDataset {
    companion object {
        fun generateQuizResults(): List<QuizResult> {
            val quizResults = arrayListOf<QuizResult>()
            for (i in 0..10000) {
                quizResults.add(
                    QuizResult(
                        quizType = QuizType.INTERVALS,
                        baseNote = notesWithOctave.keys.random(),
                        correctAnswer = randomEnum<INTERVALS>().name,
                        userAnswer = randomEnum<INTERVALS>().name,
                        date = i
                    )
                )
            }
            return quizResults.toList()
        }

        fun generateProgress(): List<Progression> {
            val progressionList = arrayListOf<Progression>()
            progressionList.add(
                Progression(
                    type = QuizType.INTERVALS,
                    question = INTERVALS.MAJOR_3RD.name,
                    date = 0
                )
            )
            progressionList.add(
                Progression(
                    type = QuizType.INTERVALS,
                    question = INTERVALS.PERFECT_5TH.name,
                    date = 0
                )
            )
            progressionList.add(
                Progression(
                    type = QuizType.INTERVALS,
                    question = INTERVALS.OCATVE.name,
                    date = 0
                )
            )
            return progressionList
        }

    }
}