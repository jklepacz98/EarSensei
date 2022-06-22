package com.example.earsensei.activities

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.earsensei.EarSenseiDBHelper
import com.example.earsensei.Note
import com.example.earsensei.R
import com.example.earsensei.SetupIntent
import com.example.earsensei.activities.quizactivities.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupStartNewActivityButton(TestQuizActivity::class.java, findViewById(R.id.button_test))
        setupStartNewActivityButton(IntervalsQuizActivity::class.java, findViewById(R.id.button_intervals))
        setupStartNewActivityButton(ChordsQuizActivity::class.java, findViewById(R.id.button_chords))
        setupStartNewActivityButton(ScalesQuizActivity::class.java, findViewById(R.id.button_scales))
        setupStartNewActivityButton(PerfectPitchQuizActivity::class.java, findViewById(R.id.button_perfect_pitch))
        setupStartNewActivityButton(MusicTheoryActivity::class.java, findViewById(R.id.button_music_theory))
        setupStartNewActivityButton(StatsMenuActivity::class.java, findViewById(R.id.button_profile))

        val earSenseiDBHelper : EarSenseiDBHelper = EarSenseiDBHelper(this)

        val buttonAddEntries : Button = findViewById(R.id.button_add_data_to_database)
        buttonAddEntries.setOnClickListener(){
            for (i in 0..20){
                val dateTime : Long = Calendar.getInstance().timeInMillis
                val contentValues : ContentValues = earSenseiDBHelper.createTestContentValues(Note.notes.keys.random(), Note.intervals.keys.random(), Note.intervals.keys.random(), dateTime)
                earSenseiDBHelper.addTestContentValues(contentValues)
            }
            for (i in 0..2){
                val dateTime : Long = Calendar.getInstance().timeInMillis
                val randomInterval = Note.intervals.keys.random()
                val contentValues : ContentValues = earSenseiDBHelper.createTestContentValues(Note.notes.keys.random(), randomInterval, randomInterval, dateTime)
                earSenseiDBHelper.addTestContentValues(contentValues)
            }
        }

    }

    fun setupStartNewActivityButton(activityClass: Class<out Activity>, button: Button){
        button.setOnClickListener(){
            SetupIntent.startNewActivity(this@MainActivity, activityClass)
        }
    }
}






