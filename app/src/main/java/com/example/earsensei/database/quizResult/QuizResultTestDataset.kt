package com.example.earsensei.database.quizResult

import com.example.earsensei.INTERVALS
import com.example.earsensei.NOTES_WITH_OCTAVE
import com.example.earsensei.database.unlockedQuestion.UnlockedQuestion
import com.example.earsensei.utils.randomEnum

class QuizResultTestDataset {
    companion object {
        fun generateQuizResults(): List<QuizResult> {
            val quizResults = arrayListOf<QuizResult>()
            for (i in 0..10000) {
                quizResults.add(
                    QuizResult(
                        quizType = INTERVALS.getType(),
                        baseNote = NOTES_WITH_OCTAVE.keys.random(),
                        correctAnswer = randomEnum<INTERVALS>().name,
                        userAnswer = randomEnum<INTERVALS>().name,
                        date = i.toLong()
                    )
                )
            }
            return quizResults.toList()
        }

        fun generateProgress(): List<UnlockedQuestion> {
            val progressionList = arrayListOf<UnlockedQuestion>()
            progressionList.add(
                UnlockedQuestion(
                    type = INTERVALS.getType(),
                    question = INTERVALS.MAJOR_3RD.name
                )
            )
            progressionList.add(
                UnlockedQuestion(
                    type = INTERVALS.getType(),
                    question = INTERVALS.MINOR_9TH.name
                )
            )
            progressionList.add(
                UnlockedQuestion(
                    type = INTERVALS.getType(),
                    question = INTERVALS.OCATVE.name
                )
            )
            return progressionList
        }

    }
}