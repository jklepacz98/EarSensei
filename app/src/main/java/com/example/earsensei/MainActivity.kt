package com.example.earsensei

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.earsensei.quizActivities.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO delete line below
        Log.v("tag", "jezu")

        val dbHelper = EarSenseiDBHelper(this)
        dbHelper.addContentValues(dbHelper.createContentValues("C4", "A5", "D3", 0))
        dbHelper.addContentValues(dbHelper.createContentValues("C4", "A5", "D3", 0))
        dbHelper.addContentValues(dbHelper.createContentValues("A4", "A5", "D3", 0))
        dbHelper.readAllData()

        setupStartNewActivityButton(TestQuizActivity::class.java, findViewById(R.id.button_test))
        setupStartNewActivityButton(IntervalsQuizActivity::class.java, findViewById(R.id.button_intervals))
        setupStartNewActivityButton(ChordsQuizActivity::class.java, findViewById(R.id.button_chords))
        setupStartNewActivityButton(ScalesQuizActivity::class.java, findViewById(R.id.button_scales))
        setupStartNewActivityButton(PerfectPitchQuizActivity::class.java, findViewById(R.id.button_perfect_pitch))
        setupStartNewActivityButton(MusicTheoryActivity::class.java, findViewById(R.id.button_music_theory))
        setupStartNewActivityButton(ProfileActivity::class.java, findViewById(R.id.button_profile))


    }

    fun setupStartNewActivityButton(activityClass: Class<out Activity>, button: Button){
        button.setOnClickListener(){
            val intent = Intent(this, activityClass)
            startActivity(intent)
        }
    }
}






