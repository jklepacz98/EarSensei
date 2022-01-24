package com.example.earsensei

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.earsensei.quiz.IntervalsActivity

class IntervalsSettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervals_settings)
        val button : Button = findViewById(R.id.next_button)
        button.setOnClickListener(){
            val intent = Intent(this, IntervalsActivity::class.java)
            startActivity(intent)
        }
    }
}