package com.example.earsensei.intervalsquiz.View

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.earsensei.NotesPlayer
import com.example.earsensei.database.Answer
import com.example.earsensei.databinding.ActivityIntervalsQuizBinding
import com.example.earsensei.intervalsquiz.ViewModel.IntervalsQuizViewModel
import com.example.earsensei.utils.QuizState

class IntervalsFragment : Fragment(), IntervalsQuizAdapter.RecyclerViewClickListener {

    private var answers: List<Answer> = listOf()
    private lateinit var binding: ActivityIntervalsQuizBinding
    private val viewModel: IntervalsQuizViewModel by lazy {
        val provider = ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ViewModelProvider(this, provider).get(IntervalsQuizViewModel::class.java)
    }
    private lateinit var notesPlayer: NotesPlayer

    override fun onAttach(context: Context) {
        super.onAttach(context)
        notesPlayer = NotesPlayer(context)
    }

    override fun onClick(position: Int) {
        viewModel.checkAnswer(position)
        viewModel.showToast()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = ActivityIntervalsQuizBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupPlayButton()
        setupNextButton()
        setupProgressObserver()
        setupProgressMaxObserver()
        setupFirstNotes()
        setupNotesObserver()
        return binding.root
    }

    //todo chyba da się to lepiej podzielić
    fun setupRecyclerView() {
        val answersAdapter = IntervalsQuizAdapter(answers, this)
        binding.rvAnswers.adapter = answersAdapter
        binding.rvAnswers.layoutManager = GridLayoutManager(activity, 3)
        viewModel.answers.observe(viewLifecycleOwner) {
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
        viewModel.state.observe(viewLifecycleOwner) {
            binding.nextButton.visibility = when (it) {
                QuizState.ANSWERED -> View.VISIBLE
                else -> View.INVISIBLE
            }
        }
    }

    fun setupProgressObserver() {
        viewModel.progress.observe(viewLifecycleOwner) {
            binding.progressBar.progress = it
            if (binding.progressBar.progress >= binding.progressBar.max) {
                // TODO:
            }
        }
    }

    fun setupProgressMaxObserver() {
        viewModel.progressMax.observe(viewLifecycleOwner) {
            binding.progressBar.max = it
        }
    }

    fun setupFirstNotes() {
        notesPlayer.setNotes(viewModel.getNotes())
        notesPlayer.playMultipleNotes()
    }

    fun setupNotesObserver() {
        viewModel.quizModel.observe(viewLifecycleOwner) {
            notesPlayer.setNotes(viewModel.getNotes())
        }
    }
}