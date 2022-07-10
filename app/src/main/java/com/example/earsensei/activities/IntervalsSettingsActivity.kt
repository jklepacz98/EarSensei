package com.example.earsensei.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.earsensei.R

class IntervalsSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervals_settings)
        val button : Button = findViewById(R.id.next_button)
        button.setOnClickListener(){
            //todo
            //val intent = Intent(this, IntervalsQuizActivity::class.java)
            startActivity(intent)
        }
    }
}