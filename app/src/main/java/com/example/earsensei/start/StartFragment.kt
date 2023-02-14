package com.example.earsensei.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.earsensei.Chords
import com.example.earsensei.Intervals
import com.example.earsensei.Scales
import com.example.earsensei.databinding.FragmentStartBinding
import com.example.earsensei.utils.safeNavigate

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater)
        setupButtons()
        return binding.root
    }

    private fun setupButtons() {
        binding.buttonIntervals.setOnClickListener {
            safeNavigate(StartFragmentDirections.actionStartFragmentToQuizFragment(Intervals.quizType))
        }
        binding.buttonChords.setOnClickListener {
            safeNavigate(StartFragmentDirections.actionStartFragmentToQuizFragment(Chords.quizType))
        }
        binding.buttonScales.setOnClickListener {
            safeNavigate(StartFragmentDirections.actionStartFragmentToQuizFragment(Scales.quizType))
        }
        binding.buttonStats.setOnClickListener {
            safeNavigate(StartFragmentDirections.actionStartFragmentToStatsFragment())
        }
        binding.buttonMusicTheory.setOnClickListener {
            safeNavigate(StartFragmentDirections.actionStartFragmentToMusicTheoryMenuFragment())
        }
    }
}