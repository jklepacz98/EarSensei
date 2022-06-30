package com.example.earsensei.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.earsensei.R
import com.example.earsensei.activities.quiz.IntervalsQuizActivity

class IntervalsSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervals_settings)
        val button : Button = findViewById(R.id.next_button)
        button.setOnClickListener(){
            val intent = Intent(this, IntervalsQuizActivity::class.java)
            startActivity(intent)
        }
    }
}