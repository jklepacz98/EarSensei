package com.example.earsensei.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.earsensei.Chords
import com.example.earsensei.Intervals
import com.example.earsensei.PerfectPitches
import com.example.earsensei.Scales
import com.example.earsensei.databinding.FragmentStartBinding
import com.example.earsensei.utils.navigate

class StartFragment : Fragment() {
    private lateinit var binding: FragmentStartBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater)
        setupButtons()
        return binding.root
    }

    private fun setupButtons() {
        binding.buttonIntervals.setOnClickListener {
            //todo
            navigate(StartFragmentDirections.actionStartFragmentToQuizFragment(Intervals.type))
        }
        binding.buttonChords.setOnClickListener {
            this@StartFragment.navigate(
                StartFragmentDirections.actionStartFragmentToQuizFragment(
                    Chords.type
                )
            )
        }
        binding.buttonScales.setOnClickListener {
            this@StartFragment.navigate(
                StartFragmentDirections.actionStartFragmentToQuizFragment(Scales.type)
            )
        }
        binding.buttonPerfectPitch.setOnClickListener {
            this@StartFragment.navigate(
                StartFragmentDirections.actionStartFragmentToQuizFragment(PerfectPitches.type)
            )
        }
        binding.buttonStats.setOnClickListener {
            this@StartFragment.navigate(StartFragmentDirections.actionStartFragmentToStatsFragment())
        }
    }
}