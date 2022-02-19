package com.example.earsensei.quiz

import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.earsensei.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestActivity : AppCompatActivity() {


    val answerNote : NotePlayer = notePlayers.random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val playButton : ImageButton = findViewById(R.id.button_play)
        var job : Job = Job()
        playButton.setOnClickListener() {
            job.cancel()
            job = MainScope().launch {
                answerNote.play(baseContext)
                delay(850)
                notePlayers[1].play(baseContext)
            }
        }
        val gridLayout : GridLayout = findViewById(R.id.buttons_grid)
        buttonsGridCreator.createButtonsGrid(this, gridLayout, notes.keys.toList());
    }


    fun checkIfNotesAreTheSame(correctNote : Int, userNote : Int) :  Boolean{
        return (userNote% OCTAVE_SIZE == correctNote)
    }
}