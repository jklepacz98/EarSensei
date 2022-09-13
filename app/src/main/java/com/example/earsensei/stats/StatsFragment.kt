package com.example.earsensei.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.earsensei.Chords
import com.example.earsensei.Intervals
import com.example.earsensei.PerfectPitches
import com.example.earsensei.Scales
import com.example.earsensei.databinding.FragmentStatsBinding
import com.example.earsensei.utils.navigate

class StatsFragment : Fragment() {
    private lateinit var binding: FragmentStatsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatsBinding.inflate(layoutInflater)
        setupButtons()
        return binding.root
    }

    private fun setupButtons() {
        binding.buttonIntervals.setOnClickListener {
            this@StatsFragment.navigate(
                StatsFragmentDirections.actionStatsFragmentToChartFragment(
                    Intervals.type
                )
            )
        }
        binding.buttonChords.setOnClickListener {
            this@StatsFragment.navigate(
                StatsFragmentDirections.actionStatsFragmentToChartFragment(
                    Chords.type
                )
            )
        }
        binding.buttonScales.setOnClickListener {
            this@StatsFragment.navigate(
                StatsFragmentDirections.actionStatsFragmentToChartFragment(
                    Scales.type
                )
            )
        }
        binding.buttonPerfectPitch.setOnClickListener {
            this@StatsFragment.navigate(
                StatsFragmentDirections.actionStatsFragmentToChartFragment(
                    PerfectPitches.type
                )
            )
        }
    }
}