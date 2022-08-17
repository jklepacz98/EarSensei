package com.example.earsensei.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.earsensei.R
import com.example.earsensei.SetupIntent
import com.example.earsensei.activities.quiz.ChordsQuizActivity
import com.example.earsensei.activities.quiz.ScalesQuizActivity
import com.example.earsensei.graphs.StatsMenuActivity
import com.example.earsensei.intervalsquiz.View.IntervalsQuizActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo
        setupStartNewActivityButton(
            IntervalsQuizActivity::class.java,
            findViewById(R.id.button_intervals)
        )
        //setupStartNewActivityButton(IntervalsQuizActivity::class.java, findViewById(R.id.button_intervals))
        setupStartNewActivityButton(
            ChordsQuizActivity::class.java,
            findViewById(R.id.button_chords)
        )
        setupStartNewActivityButton(
            ScalesQuizActivity::class.java,
            findViewById(R.id.button_scales)
        )
        setupStartNewActivityButton(
            StatsMenuActivity::class.java,
            findViewById(R.id.button_profile)
        )

        val buttonAddEntries: Button = findViewById(R.id.button_add_data_to_database)
    }

    fun setupStartNewActivityButton(activityClass: Class<out Activity>, button: Button) {
        button.setOnClickListener() {
            SetupIntent.startNewActivity(this@MainActivity, activityClass)
        }
    }
}






