package com.example.earsensei.activities

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.earsensei.*
import com.example.earsensei.activities.quizactivities.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupStartNewActivityButton(IntervalsQuizActivity::class.java, findViewById(R.id.button_intervals))
        setupStartNewActivityButton(ChordsQuizActivity::class.java, findViewById(R.id.button_chords))
        setupStartNewActivityButton(ScalesQuizActivity::class.java, findViewById(R.id.button_scales))
        setupStartNewActivityButton(PerfectPitchQuizActivity::class.java, findViewById(R.id.button_perfect_pitch))
        setupStartNewActivityButton(MusicTheoryActivity::class.java, findViewById(R.id.button_music_theory))
        setupStartNewActivityButton(StatsMenuActivity::class.java, findViewById(R.id.button_profile))

        val earSenseiDBHelper : EarSenseiDBHelper = EarSenseiDBHelper(this)

        val buttonAddEntries : Button = findViewById(R.id.button_add_data_to_database)
        buttonAddEntries.setOnClickListener(){
            val addEntriesToDB : AddEntriesToDB = AddEntriesToDB(this@MainActivity)
            addEntriesToDB.addTestEntries(20)
            addEntriesToDB.addIntervalEntries(100)
            addEntriesToDB.addChordEntries(2)
            addEntriesToDB.addScaleEntries(100)
            addEntriesToDB.addPerfectPitchEntries(100)
        }

    }

    fun setupStartNewActivityButton(activityClass: Class<out Activity>, button: Button){
        button.setOnClickListener(){
            SetupIntent.startNewActivity(this@MainActivity, activityClass)
        }
    }
}






