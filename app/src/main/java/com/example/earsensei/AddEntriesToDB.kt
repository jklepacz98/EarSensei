package com.example.earsensei

import android.content.ContentValues
import android.content.Context
import java.util.*

class AddEntriesToDB(context: Context) {
    val earSenseiDBHelper : EarSenseiDBHelper = EarSenseiDBHelper(context)
    fun addTestEntries(numberOfEntries : Int){
        for (i in 0..20){
            val dateTime : Long = Calendar.getInstance().timeInMillis
            val contentValues : ContentValues = earSenseiDBHelper.createTestContentValues(Note.notes.keys.random(), Note.intervals.keys.random(), Note.intervals.keys.random(), dateTime)
            earSenseiDBHelper.addTestContentValues(contentValues)
        }
    }

    fun addIntervalEntries(numberOfEntries: Int){
        for (i in 0..numberOfEntries){
            val dateTime : Long = Calendar.getInstance().timeInMillis
            val contentValues : ContentValues = earSenseiDBHelper.createIntervalsContentValues(Note.notes.keys.random(), Note.intervals.keys.random(), Note.intervals.keys.random(), dateTime)
            earSenseiDBHelper.addIntervalsContentValues(contentValues)
        }
    }

    fun addChordEntries(numberOfEntries: Int){
        for (i in 0..numberOfEntries){
            val dateTime : Long = Calendar.getInstance().timeInMillis
            val contentValues : ContentValues = earSenseiDBHelper.createChordsContentValues(Note.notes.keys.random(), Note.intervals.keys.random(), Note.intervals.keys.random(), dateTime)
            earSenseiDBHelper.addChordsContentValues(contentValues)
        }
    }

    fun addScaleEntries(numberOfEntries: Int){
        for (i in 0..numberOfEntries){
            val dateTime : Long = Calendar.getInstance().timeInMillis
            val contentValues : ContentValues = earSenseiDBHelper.createScalesContentValues(Note.notes.keys.random(), Note.intervals.keys.random(), Note.intervals.keys.random(), dateTime)
            earSenseiDBHelper.addScalesContentValues(contentValues)
        }
    }

    fun addPerfectPitchEntries(numberOfEntries: Int){
        for (i in 0..numberOfEntries){
            val dateTime : Long = Calendar.getInstance().timeInMillis
            val contentValues : ContentValues = earSenseiDBHelper.createPerfectPitchContentValues(Note.notes.keys.random(), Note.intervals.keys.random(), Note.intervals.keys.random(), dateTime)
            earSenseiDBHelper.addPerfectPitchContentValues(contentValues)
        }
    }


}