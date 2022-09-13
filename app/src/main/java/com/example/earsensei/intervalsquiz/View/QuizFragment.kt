package com.example.earsensei.intervalsquiz.View

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
import com.example.earsensei.intervalsquiz.ViewModel.QuizViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class QuizFragment : Fragment(), QuizAdapter.RecyclerViewClickListener {

    private val args: QuizFragmentArgs by navArgs()

    private lateinit var binding: FragmentIntervalsBinding
    private val viewModel by viewModel<QuizViewModel> {
        val type = args.type
        parametersOf(type)
    }
    private val answersAdapter = QuizAdapter(listOf(), this)


    override fun onClick(position: Int) {
        viewModel.onAnswerClick(position)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
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
        //todo
        setupMakeToastObserver()
        return binding.root
    }

    fun setupRecyclerView() {
        binding.rvAnswers.adapter = answersAdapter
        binding.rvAnswers.layoutManager = GridLayoutManager(activity, 3)

    }

    fun setupAnswersObserver() {
        viewModel.answers.observe(viewLifecycleOwner) {
            answersAdapter.changeList(it)
        }
    }

    fun setupPlayButton() {
        binding.buttonPlay.setOnClickListener() {
            viewModel.playNotes()
        }
    }

    fun setupNextButton() {
        binding.nextButton.setOnClickListener {
            viewModel.nextQuiz()
        }
    }

    fun setupIsNextButtonVisibleObserver() {
        val nextButton = binding.nextButton
        viewModel.isNextButtonVisible.observe(viewLifecycleOwner) {
            if (it) nextButton.visibility = View.VISIBLE
            else nextButton.visibility = View.INVISIBLE
        }
    }

    fun setupIsAnsweredObserver() {
        viewModel.isAnswered.observe(viewLifecycleOwner) {
            binding.nextButton.visibility = when (it) {
                true -> View.VISIBLE
                else -> View.INVISIBLE
            }
            binding.rvAnswers
        }
    }

    fun setupProgressObserver() {
        viewModel.progress.observe(viewLifecycleOwner) {
            binding.progressBar.progress = it
        }
    }

    fun setupProgressMaxObserver() {
        viewModel.progressMax.observe(viewLifecycleOwner) {
            binding.progressBar.max = it
        }
    }

    fun setupGoBackObserver() {
        viewModel.goBack.observe(viewLifecycleOwner) {
            if (it) findNavController().popBackStack()
        }
    }

    fun setupMakeToastObserver() {
        viewModel.makeToast.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

}