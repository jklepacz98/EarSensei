package com.example.earsensei.quizActivities

import android.content.Context
import android.icu.text.TimeZoneNames
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.earsensei.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TestQuizActivity : AppCompatActivity() {


    //val testQuiz : TestQuiz = TestQuiz(intervals)

    val listOfNotes : List<Note> = listOf(notePlayers[0], notePlayers[1])


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val playButton : ImageButton = findViewById(R.id.button_play)
        val notesPlayer : NotesPlayer = NotesPlayer(this, listOfNotes)
        playButton.setOnClickListener(){
            notesPlayer.playNotes()
        }
        val gridLayout : GridLayout = findViewById(R.id.buttons_grid)
        ButtonsGridCreator.createButtonsGrid(this, gridLayout, chords.keys.toList())
    }


//    fun checkIfNotesAreTheSame(correctNote : Int, userNote : Int) :  Boolean{
//        return (userNote% OCTAVE_SIZE == correctNote)
//    }
}