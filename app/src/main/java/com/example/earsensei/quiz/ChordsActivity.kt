package com.example.earsensei.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import com.example.earsensei.Note
import com.example.earsensei.R
import com.example.earsensei.chords
import com.example.earsensei.notes
import kotlinx.coroutines.*

class ChordsActivity : AppCompatActivity() {
    val context = this
    val answerNote : Note = notes.random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chords)

        val playButton : ImageButton = findViewById(R.id.button_play)
        var job : Job = Job()
        playButton.setOnClickListener() {
            job.cancel()
            job = MainScope().launch {
                answerNote.play(context)
                delay(850)
                notes[1].play(context)
            }
        }
        createGrid(chords)
    }


    fun createGrid(chords : Map<String, Array<Int>>){
        chords.forEach{
            createButtonInGrid(it.key, findViewById(R.id.buttons_grid))
        }
    }

    fun createButtonInGrid(name : String, view: GridLayout){

        var button = Button(this)
        button.setText(name)
        view.addView(button)
        button.setBackground(ContextCompat.getDrawable(context,R.drawable.default_round_button))
    }
}