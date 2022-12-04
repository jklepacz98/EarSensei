package com.example.earsensei.quiz.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.earsensei.databinding.FragmentIntervalsBinding
import com.example.earsensei.quiz.ViewModel.QuizViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class QuizFragment : Fragment() {

    private val args: QuizFragmentArgs by navArgs()

    private lateinit var binding: FragmentIntervalsBinding
    private val viewModel by viewModel<QuizViewModel> {
        val type = args.type
        parametersOf(type)
    }
    private val answersAdapter = QuizAdapter() { answer -> viewModel.onAnswerClick(answer) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreate(savedInstanceState)
        binding = FragmentIntervalsBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupAnswersObserver()
        setupPlayButton()
        setupNextButton()
        setupIsNextButtonVisibleObserver()
        setupIsAnsweredObserver()
        setupProgressObserver()
        setupProgressMaxObserver()
        setupGoBackObserver()
        setupToastEventObserver()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.rvAnswers.adapter = answersAdapter
        binding.rvAnswers.layoutManager = GridLayoutManager(activity, 3)

    }

    private fun setupAnswersObserver() {
        viewModel.answers.observe(viewLifecycleOwner) {
            answersAdapter.updateList(it)
        }
    }

    private fun setupPlayButton() {
        binding.buttonPlay.setOnClickListener() {
            viewModel.playNotes()
        }
    }

    private fun setupNextButton() {
        binding.nextButton.setOnClickListener {
            viewModel.nextQuiz()
        }
    }

    private fun setupIsNextButtonVisibleObserver() {
        val nextButton = binding.nextButton
        viewModel.isNextButtonVisible.observe(viewLifecycleOwner) {
            if (it) nextButton.visibility = View.VISIBLE
            else nextButton.visibility = View.INVISIBLE
        }
    }

    private fun setupIsAnsweredObserver() {
        viewModel.isAnswered.observe(viewLifecycleOwner) {
            binding.nextButton.visibility = when (it) {
                true -> View.VISIBLE
                else -> View.INVISIBLE
            }
            binding.rvAnswers
        }
    }

    private fun setupProgressObserver() {
        viewModel.currentProgress.observe(viewLifecycleOwner) {
            binding.progressBar.progress = it
        }
    }

    private fun setupProgressMaxObserver() {
        viewModel.maximumProgress.observe(viewLifecycleOwner) {
            binding.progressBar.max = it
        }
    }

    private fun setupGoBackObserver() {
        viewModel.goBack.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

    private fun setupToastEventObserver() {
        viewModel.toastEvent.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}