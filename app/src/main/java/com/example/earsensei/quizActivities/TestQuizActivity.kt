package com.example.earsensei.quizActivities

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.icu.text.TimeZoneNames
import android.os.Bundle
import android.util.Log
import android.view.SurfaceControl
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.earsensei.*
import com.example.earsensei.quiz.TestQuiz
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

class TestQuizActivity : AppCompatActivity() {

    val earSenseiDBHelper : EarSenseiDBHelper = EarSenseiDBHelper(this)

    val listOfNotes : List<Note> = listOf(Note.notePlayers[0], Note.notePlayers[1], Note.notePlayers[2])

    val testQuiz : TestQuiz = TestQuiz(Note.intervals)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
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
                //TODO
                val date : Long = Calendar.getInstance().timeInMillis
                val contentValues : ContentValues = earSenseiDBHelper.createContentValues(Note.notePlayers[7].name, testQuiz.getCorrectAnswer(), buttonText, date)
                earSenseiDBHelper.addContentValues(contentValues)
                //TODO
                earSenseiDBHelper.readAllData().forEach(){Log.d("Tag", it.toString())}
                //Log.d("TAG", earSenseiDBHelper.readAllData().toString())
//                Toast.makeText(this, buttonText, Toast.LENGTH_SHORT).show()
                Toast.makeText(this, testQuiz.getCorrectAnswer() + "\n"+ testQuiz.checkAnswer(buttonText).toString(), Toast.LENGTH_SHORT).show()

                //testQuiz.checkAnswer(buttonText)
                if(testQuiz.checkAnswer(buttonText)){
                    val intent = Intent(this, TestQuizActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    this.startActivity(intent)
                }

            }
        }
    }
}