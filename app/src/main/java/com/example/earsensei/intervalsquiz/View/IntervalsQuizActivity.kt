package com.example.earsensei.intervalsquiz.View

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.earsensei.NotesPlayer
import com.example.earsensei.QuizState
import com.example.earsensei.database.Answer
import com.example.earsensei.databinding.ActivityIntervalsQuizBinding
import com.example.earsensei.intervalsquiz.ViewModel.IntervalsQuizViewModel

class IntervalsQuizActivity : AppCompatActivity(), IntervalsQuizAdapter.RecyclerViewClickListener {

    private var answers: List<Answer> = listOf()
    private lateinit var binding: ActivityIntervalsQuizBinding
    private val viewModel: IntervalsQuizViewModel by lazy {
        val provider = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ViewModelProvider(this, provider).get(IntervalsQuizViewModel::class.java)
    }
    private val notesPlayer: NotesPlayer = NotesPlayer(this)

    override fun onClick(position: Int) {
        viewModel.checkAnswer(position)
        viewModel.showToast()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntervalsQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        setupPlayButton()
        setupNextButton()
        setupProgressObserver()
        setupProgressMaxObserver()
        setupFirstNotes()
        setupNotesObserver()
    }

    //todo chyba da się to lepiej podzielić
    fun setupRecyclerView() {
        val answersAdapter = IntervalsQuizAdapter(answers, this)
        binding.rvAnswers.adapter = answersAdapter
        binding.rvAnswers.layoutManager = GridLayoutManager(this, 3)
        viewModel.answers.observe(this) {
            answers = viewModel.answers.value ?: listOf()
            answersAdapter.changeList(answers)
        }
    }

    fun setupPlayButton() {
        binding.buttonPlay.setOnClickListener() {
            notesPlayer.playMultipleNotes()
        }
    }

    fun setupNextButton() {
        binding.nextButton.setOnClickListener {
            viewModel.nextQuiz()
            notesPlayer.playMultipleNotes()
        }
        viewModel.state.observe(this) {
            binding.nextButton.visibility = when (it) {
                QuizState.ANSWERED -> View.VISIBLE
                else -> View.INVISIBLE
            }
        }
    }

    fun setupProgressObserver() {
        viewModel.progress.observe(this) {
            binding.progressBar.progress = it
            if (binding.progressBar.progress >= binding.progressBar.max) {
                this.finish()
            }
        }
    }

    fun setupProgressMaxObserver() {
        viewModel.progressMax.observe(this) {
            binding.progressBar.max = it
        }
    }

    fun setupFirstNotes() {
        notesPlayer.setNotes(viewModel.getNotes())
        notesPlayer.playMultipleNotes()
    }

    fun setupNotesObserver() {
        viewModel.quizModel.observe(this) {
            notesPlayer.setNotes(viewModel.getNotes())
        }
    }
}