package com.example.earsensei.activities.quizactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.earsensei.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class IntervalsQuizActivity : AppCompatActivity() {
    val context = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervals)


        val playButton: ImageButton = findViewById(R.id.button_play)
        var job: Job = Job()
        playButton.setOnClickListener() {
            job.cancel()
            job = MainScope().launch {

            }
        }

    }
}