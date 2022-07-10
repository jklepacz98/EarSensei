package com.example.earsensei

import android.os.Build.VERSION_CODES.M
import com.example.earsensei.intervalsquiz.Model.AnswerModel

class QuizNotesGenerator {
    companion object{
        fun generateIntervalsNotes(quizModel: QuizModel) : List<Int>{
            val notes : MutableList<Int> = mutableListOf()
            MusicTerminology.notesWithOctave[quizModel.baseNote]?.let { notes.add(it) }
            val nextNote : Int = (MusicTerminology.notesWithOctave[quizModel.baseNote] ?: 0) + (MusicTerminology.intervals[quizModel.correctAnswer] ?: 0)
            notes.add(nextNote)
            return notes
        }
    }
}