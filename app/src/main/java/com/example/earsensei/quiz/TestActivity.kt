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
//
//    fun createGrid(notes : Map<Int, String>){
//        val gridLayout : GridLayout = findViewById(R.id.buttons_grid)
//        notes.forEach{
//            var button = createButton(it.key, gridLayout)
//            gridLayout.addView(button)
//        }
//    }
//
//    fun createButton(noteWithoutOctaveNumber : Int, viewGroup: ViewGroup) : Button{
//        var inflater : LayoutInflater = layoutInflater
//        var button = inflater.inflate(R.layout.fragment_button, viewGroup, false) as Button
//        button.setText(notesWithoutOctaveNumber.get(noteWithoutOctaveNumber))
//        return button
//    }

    fun checkIfNotesAreTheSame(correctNote : Int, userNote : Int) :  Boolean{
        return (userNote% OCTAVE_SIZE == correctNote)
    }
}