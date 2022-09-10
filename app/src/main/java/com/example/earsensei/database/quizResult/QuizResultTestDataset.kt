package com.example.earsensei.database.quizResult

import com.example.earsensei.Intervals
import com.example.earsensei.NOTES_WITH_OCTAVE
import com.example.earsensei.database.unlockedQuestion.UnlockedQuestion

class QuizResultTestDataset {
    companion object {
        fun generateQuizResults(): List<QuizResult> {
            val quizResults = arrayListOf<QuizResult>()
            for (i in 0..10000) {
                quizResults.add(
                    QuizResult(
                        quizType = Intervals.type,
                        baseNote = NOTES_WITH_OCTAVE.keys.random(),
                        correctAnswer = Intervals.list.random().name,
                        userAnswer = Intervals.list.random().name,
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
                    type = Intervals.type,
                    question = Intervals.list.get(0).name
                )
            )
            progressionList.add(
                UnlockedQuestion(
                    type = Intervals.type,
                    question = Intervals.list.get(1).name
                )
            )
            progressionList.add(
                UnlockedQuestion(
                    type = Intervals.type,
                    question = Intervals.list.get(2).name
                )
            )
            return progressionList
        }

    }
}