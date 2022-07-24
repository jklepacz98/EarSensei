package com.example.earsensei.intervalsquiz.View

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.earsensei.NotesPlayer
import com.example.earsensei.QuizState
import com.example.earsensei.R
import com.example.earsensei.intervalsquiz.Model.AnswerModel
import com.example.earsensei.intervalsquiz.ViewModel.IntervalsQuizViewModel

class IntervalsQuizActivity : AppCompatActivity(), IntervalsQuizAdapter.RecyclerViewClickListener {

    var answers: List<AnswerModel> = listOf()

    val viewModel: IntervalsQuizViewModel by lazy {
        ViewModelProvider(this).get(IntervalsQuizViewModel::class.java)
    }

    val notesPlayer: NotesPlayer = NotesPlayer(this)


    override fun onClick(position: Int) {
        //todo
        //makeToast(position.toString())
        viewModel.checkAnswer(position)
    }

    fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intervals_quiz)

        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        val nextButton: Button = findViewById(R.id.next_button)
        val playButton: ImageButton = findViewById(R.id.button_play)
        val answersAdapter: IntervalsQuizAdapter = IntervalsQuizAdapter(answers, this)

        val rvAnswers: RecyclerView = findViewById(R.id.rv_answers)
        rvAnswers.adapter = answersAdapter
        rvAnswers.layoutManager = GridLayoutManager(this, 3)

        viewModel.toastMessage.observe(this) {
            makeToast(it)
        }

        viewModel.answers.observe(this) {
            Toast.makeText(this, "obserwnąłem zmiany answers", Toast.LENGTH_SHORT).show()
            answers = viewModel.answers.value ?: listOf()
            answersAdapter.changeList(answers)
        }

        viewModel.progress.observe(this) {
            progressBar.progress = it
            //todo
            if (progressBar.progress >= progressBar.max) {
                this.finish()
            }
        }

        viewModel.progressMax.observe(this) {
            progressBar.max = it
        }

        viewModel.state.observe(this) {
            if (viewModel.state.value == QuizState.ANSWERED) {
                nextButton.visibility = View.VISIBLE
            } else {
                nextButton.visibility = View.INVISIBLE
            }
        }

        viewModel.quizModel.observe(this) {
            notesPlayer.setNotes(viewModel.getNotes())
        }


        notesPlayer.setNotes(viewModel.getNotes())
        Log.d("cos1", viewModel.getNotes().toString())
        notesPlayer.playMultipleNotes()


        nextButton.setOnClickListener {
            viewModel.nextQuiz()
            notesPlayer.playMultipleNotes()
        }

        playButton.setOnClickListener() {
            notesPlayer.playMultipleNotes()
        }
    }
}