package com.example.earsensei.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.Toast
import com.example.earsensei.*
import kotlinx.coroutines.*

class PerfectPitchActivity : AppCompatActivity() {

    val context = this
    val answerNote : NotePlayer = notePlayers.random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfect_pitch)
        val playButton : ImageButton = findViewById(R.id.button_play)
        playButton.setOnClickListener() {
            //MainScope().cancel()
            MainScope().launch {
                answerNote.play(context);
//                delay(850)
//                notes[18].play(context);
            }
        }

    }


    fun checkIfNotesAreTheSame(correctNote : Int, userNote : Int) :  Boolean{
        return (userNote% OCTAVE_SIZE == correctNote)
    }



}