package com.example.earsensei.database.quizResult

import com.example.earsensei.Chords
import com.example.earsensei.Intervals
import com.example.earsensei.PerfectPitches
import com.example.earsensei.Scales
import com.example.earsensei.database.unlockedQuestion.UnlockedQuestion

class QuizResultTestDataset {
    companion object {
        fun generateQuizResults(): List<QuizResult> {
            val quizResults = arrayListOf<QuizResult>()
//            for (i in 0..10000) {
//                quizResults.add(
//                    QuizResult(
//                        quizType = Intervals.type,
//                        baseNote = NOTES_WITH_OCTAVE.keys.random(),
//                        correctAnswer = Intervals.list.random().name,
//                        userAnswer = Intervals.list.random().name,
//                        date = i.toLong()
//                    )
//                )
//            }
            return quizResults.toList()
        }

        fun generateProgress(): List<UnlockedQuestion> {
            val progressionList = arrayListOf<UnlockedQuestion>()
            progressionList.add(
                UnlockedQuestion(
                    type = Intervals.type,
                    question = Intervals.list.get(3).name
                )
            )
            progressionList.add(
                UnlockedQuestion(
                    type = Intervals.type,
                    question = Intervals.list.get(6).name
                )
            )
            progressionList.add(
                UnlockedQuestion(
                    type = Intervals.type,
                    question = Intervals.list.get(11).name
                )
            )
            //Chords
            progressionList.add(
                UnlockedQuestion(
                    type = Chords.type,
                    question = Chords.list.get(0).name
                )
            )
            progressionList.add(
                UnlockedQuestion(
                    type = Chords.type,
                    question = Chords.list.get(1).name
                )
            )
            //Scales
            progressionList.add(
                UnlockedQuestion(
                    type = Scales.type,
                    question = Scales.list.get(0).name
                )
            )
            progressionList.add(
                UnlockedQuestion(
                    type = Scales.type,
                    question = Scales.list.get(1).name
                )
            )
            //PerfectPitch
            progressionList.add(
                UnlockedQuestion(
                    type = PerfectPitches.type,
                    question = PerfectPitches.list.get(0).name
                )
            )
            //PerfectPitch
            progressionList.add(
                UnlockedQuestion(
                    type = PerfectPitches.type,
                    question = PerfectPitches.list.get(2).name
                )
            )

            return progressionList
        }

    }
}