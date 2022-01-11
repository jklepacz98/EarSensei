package com.example.earsensei

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.coroutines.*


public class PerfectPitchActivity : AppCompatActivity() {

    val context = this
    val answerNote : Note = notes.random()

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
        createGrid(notesWithoutOctaveNumber)


    }

    fun createGrid(notes : Map<Int, String>){
        notes.forEach{
            createButtonInGrid(it.key, findViewById(R.id.buttons_grid))
        }
    }

    fun createButtonInGrid(noteWithoutOctaveNumber : Int, view: GridLayout){
        var button = Button(this)
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