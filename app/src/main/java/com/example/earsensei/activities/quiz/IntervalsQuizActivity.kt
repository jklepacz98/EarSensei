package com.example.earsensei.activities.quiz

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.ProgressBar
import com.example.earsensei.*
import com.example.earsensei.quiz.IntervalsQuiz
import java.util.*

class IntervalsQuizActivity : AppCompatActivity() {

    val earSenseiDBHelper : EarSenseiDBHelper = EarSenseiDBHelper(this)



    val intervalsQuiz : IntervalsQuiz = IntervalsQuiz(MusicTerminology.intervals)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervals)

        QuizManager.quizQuestions.push(listOf(Note.notePlayers.random(), Note.notePlayers.random()))
        val listOfNotes : List<Note> = QuizManager.quizQuestions.pop()

        val playButton : ImageButton = findViewById(R.id.button_play)
        val notesPlayer : NotesPlayer = NotesPlayer(this, listOfNotes)
        playButton.setOnClickListener(){
            notesPlayer.playMultipleNotes()
        }

        val progressBar : ProgressBar = findViewById(R.id.progress_bar)
        progressBar.max = 20
        progressBar.progress = 10

        val gridLayout : GridLayout = findViewById(R.id.buttons_grid)
        val buttons : ButtonsGridCreator = ButtonsGridCreator(this, gridLayout, MusicTerminology.intervals.keys.toList())
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