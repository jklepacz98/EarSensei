package com.example.earsensei.intervalsquiz.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.earsensei.*
import com.example.earsensei.intervalsquiz.Model.AnswerModel
import com.example.earsensei.intervalsquiz.ViewModel.IntervalsQuizViewModel

class IntervalsQuizActivity : AppCompatActivity(), IntervalsQuizAdapter.RecyclerViewClickListener {


    val answers: ArrayList<AnswerModel> = arrayListOf()

    val notes: ArrayList<Int> = arrayListOf()

    val earSenseiDBHelper: EarSenseiDBHelper = EarSenseiDBHelper(this)

    val answersAdapter: IntervalsQuizAdapter = IntervalsQuizAdapter(answers, this)

    override fun onClick(position: Int) {
        answersAdapter.changeList(arrayListOf())
        Toast.makeText(this, position.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervals_quiz)

        //
//        val recordModels = earSenseiDBHelper.readAllIntervalsData()
//
//        val quizManager: QuizManager = QuizManager(recordModels, QuizType.INTERVALS, 100, earSenseiDBHelper)
//
//        val viewModel = ViewModelProvider(this).get(IntervalsQuizViewModel::class.java)
//
//        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
//        progressBar.max = 20
//        progressBar.progress = 0
//

        val arrayList: ArrayList<AnswerModel> = arrayListOf()
        for (i in 0..20) {
            val randomKey = MusicTerminology.intervals.keys.random()
            arrayList.add(AnswerModel(randomKey,
                MusicTerminology.Translations.intervals[randomKey]
                    ?: R.string.hello_blank_fragment
            )
            )
        }

        val rvAnswers: RecyclerView = findViewById(R.id.rv_answers)
        val answers: ArrayList<AnswerModel> = ArrayList<AnswerModel>(arrayList.sortedBy { MusicTerminology.intervals.get(it.name) })
        rvAnswers.adapter = answersAdapter
        rvAnswers.layoutManager = GridLayoutManager(this, 3)

        //todo
        answersAdapter.changeList(answers)

//        notes.addAll(MusicTerminology.scales.get("Major")!!.toList())
//        var listOfNotes: ArrayList<Note> = arrayListOf()
//        notes?.forEach {
//            Note.notes[it]?.let { listOfNotes.add(it) }
//        }
//
//        val playButton: ImageButton? = findViewById(R.id.button_play)
//        val notesPlayer: NotesPlayer = NotesPlayer(this, listOfNotes)
//        playButton?.setOnClickListener() {
//            notesPlayer.playMultipleNotes()
//        }
    }
}