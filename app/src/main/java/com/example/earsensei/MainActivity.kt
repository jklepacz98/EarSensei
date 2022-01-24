package com.example.earsensei

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.earsensei.quiz.ChordsActivity
import com.example.earsensei.quiz.PerfectPitchActivity
import com.example.earsensei.quiz.ScalesActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupStartNewActivityButton(IntervalsSettingsActivity::class.java, findViewById(R.id.button_intervals))
        setupStartNewActivityButton(ChordsActivity::class.java, findViewById(R.id.button_chords))
        setupStartNewActivityButton(ScalesActivity::class.java, findViewById(R.id.button_scales))
        setupStartNewActivityButton(PerfectPitchActivity::class.java, findViewById(R.id.button_perfect_pitch))
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






