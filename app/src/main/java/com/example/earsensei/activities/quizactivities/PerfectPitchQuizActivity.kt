package com.example.earsensei.activities.quizactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.earsensei.*
import kotlinx.coroutines.*

class PerfectPitchQuizActivity : AppCompatActivity() {

    val context = this
   // val answerNote : NotePlayer = notePlayers.random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfect_pitch)
        val playButton : ImageButton = findViewById(R.id.button_play)
        playButton.setOnClickListener() {
            //MainScope().cancel()
            MainScope().launch {
     //           answerNote.play(context);
//                delay(850)
//                notes[18].play(context);
            }
        }

    }






}