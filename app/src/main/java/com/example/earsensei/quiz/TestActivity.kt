package com.example.earsensei.quiz

import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.earsensei.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestActivity : AppCompatActivity() {

    val context = this
    val answerNote : Note = notes.random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
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
        createGrid(notesWithoutOctaveNumber)


    }

    fun createGrid(notes : Map<Int, String>){
        notes.forEach{
            createButtonInGrid(it.key, findViewById(R.id.buttons_grid))
        }
    }

    fun createButtonInGrid(noteWithoutOctaveNumber : Int, view: GridLayout){
        var button = Button(this)
        button.setBackground(ContextCompat.getDrawable(context,R.drawable.default_round_button))
        button.setText(notesWithoutOctaveNumber.get(noteWithoutOctaveNumber))
        button.setOnClickListener{
            if(checkIfNotesAreTheSame(answerNote.number ,noteWithoutOctaveNumber)) {
                Toast.makeText(this, "Dobra odpowiedź!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Zła odpowiedź", Toast.LENGTH_SHORT).show()

            }
        }
        view.addView(button)
    }

    fun checkIfNotesAreTheSame(correctNote : Int, userNote : Int) :  Boolean{
        return (userNote% OCTAVE_SIZE == correctNote)
    }
}