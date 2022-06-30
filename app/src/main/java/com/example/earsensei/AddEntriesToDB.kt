package com.example.earsensei

import android.content.ContentValues
import android.content.Context
import java.util.*

class AddEntriesToDB(context: Context) {
    val earSenseiDBHelper : EarSenseiDBHelper = EarSenseiDBHelper(context)

    fun addRandomIntervalEntries(numberOfEntries: Int){
        for (i in 0..numberOfEntries){
            val dateTime : Long = Calendar.getInstance().timeInMillis
            val contentValues : ContentValues = earSenseiDBHelper.createIntervalsContentValues(MusicTerminology.notes.keys.random(), QuizType.INTERVALS, MusicTerminology.intervals.keys.random(), MusicTerminology.intervals.keys.random(), dateTime)
            earSenseiDBHelper.addIntervalsContentValues(contentValues)
        }
    }

    fun addCorrectIntervalEntries(numberOfEntries: Int){
        for (i in 0..numberOfEntries){
            val dateTime : Long = Calendar.getInstance().timeInMillis
            val correctAnswer : String = MusicTerminology.intervals.keys.random()
            val contentValues : ContentValues = earSenseiDBHelper.createIntervalsContentValues(MusicTerminology.notes.keys.random(), QuizType.INTERVALS, correctAnswer, correctAnswer, dateTime)
            earSenseiDBHelper.addIntervalsContentValues(contentValues)
        }
    }



}