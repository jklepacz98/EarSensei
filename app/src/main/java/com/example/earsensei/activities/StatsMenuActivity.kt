package com.example.earsensei.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.earsensei.R

class StatsMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats_menu)

        setupStartNewActivityButton(StatsGeneralActivity::class.java, findViewById(R.id.button_test))
    }

    fun setupStartNewActivityButton(activityClass: Class<out Activity>, button: Button){
        button.setOnClickListener(){
            val intent = Intent(this, activityClass)
            intent.putExtra("TITLE", "Test")
            startActivity(intent)
        }
    }
}