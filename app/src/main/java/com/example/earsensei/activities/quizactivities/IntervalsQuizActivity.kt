package com.example.earsensei.activities.quizactivities

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import com.example.earsensei.*
import com.example.earsensei.quiz.IntervalsQuiz
import java.util.*

class IntervalsQuizActivity : AppCompatActivity() {

    val earSenseiDBHelper : EarSenseiDBHelper = EarSenseiDBHelper(this)

    val listOfNotes : List<Note> = listOf(Note.notePlayers[0], Note.notePlayers[1], Note.notePlayers[2])

    val intervalsQuiz : IntervalsQuiz = IntervalsQuiz(Note.intervals)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervals)
        val playButton : ImageButton = findViewById(R.id.button_play)
        val notesPlayer : NotesPlayer = NotesPlayer(this, listOfNotes)
        playButton.setOnClickListener(){
            notesPlayer.playNotes()
        }
        val gridLayout : GridLayout = findViewById(R.id.buttons_grid)
        val buttons : ButtonsGridCreator = ButtonsGridCreator(this, gridLayout, Note.intervals.keys.toList())
        buttons.allButtons.forEach(){
            val buttonText : String = it.text.toString()
            it.setOnClickListener(){
                val date : Long = Calendar.getInstance().timeInMillis
                val contentValues : ContentValues = earSenseiDBHelper.createIntervalsContentValues(Note.notePlayers[7].name, QuizType.INTERVALS ,intervalsQuiz.getCorrectAnswer(), buttonText, date)
                earSenseiDBHelper.addIntervalsContentValues(contentValues)
                if(intervalsQuiz.checkAnswer(buttonText)){
                    val intent = Intent(this, IntervalsQuizActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    this.startActivity(intent)
                }

            }
        }
    }
}