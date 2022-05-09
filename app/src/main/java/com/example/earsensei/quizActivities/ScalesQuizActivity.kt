package com.example.earsensei.quizActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageButton
import com.example.earsensei.R
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ScalesQuizActivity : AppCompatActivity() {
    val context = this
    //val answerNote : NotePlayer = notePlayers.random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scales)
        val playButton : ImageButton = findViewById(R.id.button_play)
        playButton.setOnClickListener() {
            MainScope().launch {
               // answerNote.play(context);
            }
        }
       // createGrid(scales)


    }

    fun createGrid(scales : Map<String, Array<Int>>){
        scales.forEach{
            createButtonInGrid(it.key, findViewById(R.id.buttons_grid))
        }
    }

    fun createButtonInGrid(name : String, view: GridLayout){
        var button = Button(this)
        button.setText(name)
        view.addView(button)
    }


}