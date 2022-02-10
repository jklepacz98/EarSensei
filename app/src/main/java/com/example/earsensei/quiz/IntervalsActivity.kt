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

    val answerNote : NotePlayer = notePlayers.random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervals)

        val answerInterval : Int = intervals.values.random()

        val notesArray : Array<NotePlayer> = notePlayers

        val notesPlayer : MultipleNotesPlayer = MultipleNotesPlayer(notesArray, context)

        val playButton : ImageButton = findViewById(R.id.button_play)
        var job : Job = Job()
        playButton.setOnClickListener() {
            job.cancel()
            job = MainScope().launch {
                notesPlayer.play()
            }
        }

    }




//    //TODO
//    fun checkIfNotesAreTheSame(correctNote : Int, userNote : Int) :  Boolean{
//        return (userNote% OCTAVE_SIZE == correctNote)
//    }
}