package com.example.earsensei.intervalsquiz.View

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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

    var answers: ArrayList<AnswerModel> = arrayListOf()

    val viewModel: IntervalsQuizViewModel by lazy {
        ViewModelProvider(this).get(IntervalsQuizViewModel::class.java)
    }

    val notesPlayer: NotesPlayer = NotesPlayer(this)


    override fun onClick(position: Int) {
        //todo
        //makeToast(position.toString())
        viewModel.checkAnswer(answers[position])
    }

    fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervals_quiz)

        //todo
        //viewModel = ViewModelProvider(this).get(IntervalsQuizViewModel::class.java)

        //todo
        //earSenseiDBHelper.prepareBasicQuestions()

        val progressBar: ProgressBar = findViewById(R.id.progress_bar)


        //todo
        //answers = viewModel.answers.value ?: arrayListOf()
        val answersAdapter: IntervalsQuizAdapter = IntervalsQuizAdapter(answers, this)

        val rvAnswers: RecyclerView = findViewById(R.id.rv_answers)
        rvAnswers.adapter = answersAdapter
        rvAnswers.layoutManager = GridLayoutManager(this, 3)

        viewModel.toastMessage.observe(this) {
            makeToast(it)
        }

        viewModel.answers.observe(this) {
            answers =viewModel.answers.value ?: arrayListOf()
            answersAdapter.changeList(answers)
        }

        viewModel.progress.observe(this) {
            progressBar.progress = it
        }

        viewModel.progressMax.observe(this) {
            progressBar.max = it
        }

        viewModel.state.observe(this) {
            //todo
            if (viewModel.state.value == QuizState.ANSWERED_CORRECT) {
                window.decorView.setBackgroundColor(Color.rgb(200, 255, 200))
            } else if (viewModel.state.value == QuizState.ANSWERED_WRONG) {
                window.decorView.setBackgroundColor(Color.rgb(255, 200, 200))
            } else {
                window.decorView.setBackgroundColor(resources.getColor(R.color.design_default_color_background))
            }
        }
//todo
        viewModel.quizModel.observe(this) {
            notesPlayer.setNotes(viewModel.getNotes())
        }


        notesPlayer.setNotes(viewModel.getNotes())


        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            viewModel.nextQuiz()
            notesPlayer.playMultipleNotes()
        }

        val playButton: ImageButton = findViewById(R.id.button_play)
        playButton.setOnClickListener() {
            notesPlayer.playMultipleNotes()
        }

    }
}