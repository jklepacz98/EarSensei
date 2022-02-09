package com.example.earsensei.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import com.example.earsensei.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class IntervalsActivity : AppCompatActivity() {
    val context = this

    val answerNote : Note = notes.random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervals)

        val answerInterval : Int = intervals.keys.random()

        val notesArray : Array<Note> = notes

        val notesPlayer : NotesPlayer = NotesPlayer(notesArray, context)

        val playButton : ImageButton = findViewById(R.id.button_play)
        var job : Job = Job()
        playButton.setOnClickListener() {
            job.cancel()
            job = MainScope().launch {
                notesPlayer.play()
            }
        }
        createGrid(intervals)
    }

    fun createGrid(notes : Map<Int, String>){
        notes.forEach{
            createButtonInGrid(it.key, findViewById(R.id.buttons_grid))
        }
    }

    fun createButtonInGrid(key : Int, view: GridLayout){
        var button = Button(this)
        button.setText(intervals.get(key))
        button.width = 300
        button.setOnClickListener{
//            if(checkIfNotesAreTheSame(answerNote.number ,key)) {
//                Toast.makeText(this, "Dobra odpowiedź!", Toast.LENGTH_SHORT).show()
//            }else{
//                Toast.makeText(this, "Zła odpowiedź", Toast.LENGTH_SHORT).show()
//
//            }
        }
        view.addView(button)
    }

//    //TODO
//    fun checkIfNotesAreTheSame(correctNote : Int, userNote : Int) :  Boolean{
//        return (userNote% OCTAVE_SIZE == correctNote)
//    }
}