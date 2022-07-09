package com.example.earsensei.activities.quiz

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.GridLayout
import android.widget.ImageButton
import android.widget.TextView
import com.example.earsensei.*
import com.example.earsensei.MusicTerminology.Companion.notes
import com.example.earsensei.activities.quiz.IntervalsQuizActivity
import com.example.earsensei.quiz.IntervalsQuiz
import java.util.*
import java.util.stream.Collectors.toList
import kotlin.collections.ArrayList


class IntervalsQuizFragment : Fragment() {

    var notes : ArrayList<Int> = arrayListOf()

    lateinit var activityContext : Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activityContext = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_intervals_quiz, container, false)



        var listOfNotes : ArrayList<Note> = arrayListOf()
        notes?.forEach {
            listOfNotes.add(Note.notePlayers[it])
        }

        val playButton : ImageButton? = view.findViewById(R.id.button_play)
        val notesPlayer : NotesPlayer = NotesPlayer(activityContext, listOfNotes)
        playButton?.setOnClickListener(){
            notesPlayer.playMultipleNotes()
        }
        val earSenseiDBHelper : EarSenseiDBHelper = EarSenseiDBHelper(activityContext)
        val intervalsQuiz : IntervalsQuiz = IntervalsQuiz(MusicTerminology.intervals)
        val gridLayout : GridLayout = view.findViewById(R.id.buttons_grid)
        val buttons : ButtonsGridCreator = ButtonsGridCreator(activityContext, gridLayout, MusicTerminology.intervals.keys.toList())
        buttons.allButtons.forEach(){
            val buttonText : String = it.text.toString()
            it.setOnClickListener(){
                val date : Long = Calendar.getInstance().timeInMillis
                //todo
                //val contentValues : ContentValues = earSenseiDBHelper.createIntervalsContentValues(Note.notePlayers[7].name, QuizType.INTERVALS ,intervalsQuiz.getCorrectAnswer(), buttonText, date)
                //earSenseiDBHelper.addIntervalsContentValues(contentValues)
                if(intervalsQuiz.checkAnswer(buttonText)){

                }
            }
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getIntegerArrayList("NOTES")?.let{ notes = it}
    }
}