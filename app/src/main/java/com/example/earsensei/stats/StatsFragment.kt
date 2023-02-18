package com.example.earsensei.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.earsensei.Chords
import com.example.earsensei.Intervals
import com.example.earsensei.Scales
import com.example.earsensei.databinding.FragmentStatsBinding
import com.example.earsensei.utils.safeNavigate

class StatsFragment : Fragment() {
    private lateinit var binding: FragmentStatsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentStatsBinding.inflate(layoutInflater)
        setupButtons()
        return binding.root
    }

    private fun setupButtons() {
        binding.run {
            buttonIntervals.setOnClickListener {
                safeNavigate(StatsFragmentDirections.actionStatsFragmentToChartFragment(Intervals.musicElementType))
            }
            buttonChords.setOnClickListener {
                safeNavigate(StatsFragmentDirections.actionStatsFragmentToChartFragment(Chords.musicElementType))
            }
            buttonScales.setOnClickListener {
                safeNavigate(StatsFragmentDirections.actionStatsFragmentToChartFragment(Scales.musicElementType))
            }
        }
    }
}