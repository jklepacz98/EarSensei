package com.example.earsensei.intervalsquiz.View

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
import com.example.earsensei.databinding.FragmentIntervalsBinding
import com.example.earsensei.intervalsquiz.ViewModel.IntervalsQuizViewModel

class IntervalsFragment : Fragment(), IntervalsQuizAdapter.RecyclerViewClickListener {

    private var answers: List<Answer> = listOf()
    private lateinit var binding: FragmentIntervalsBinding
    private val viewModel: IntervalsQuizViewModel by lazy {
        ViewModelProvider(this@IntervalsFragment).get(IntervalsQuizViewModel::class.java)
    }
    private lateinit var notesPlayer: NotesPlayer

    override fun onAttach(context: Context) {
        super.onAttach(context)
        notesPlayer = NotesPlayer(context)
    }

    override fun onClick(position: Int) {
        // TODO:
        //viewModel.checkAnswer(position)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentIntervalsBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupPlayButton()
        setupNextButton()
        setupProgressObserver()
        setupProgressMaxObserver()
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
            // TODO:
            //viewModel.nextQuiz()
            notesPlayer.playMultipleNotes()
        }
        viewModel.isAnswered.observe(viewLifecycleOwner) {
            binding.nextButton.visibility = when (it) {
                true -> View.VISIBLE
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

    fun setupNotesObserver() {
        viewModel.notes.observe(viewLifecycleOwner) {
            notesPlayer.setNotes(it)
        }
    }
}