package com.example.earsensei.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
            this@StatsFragment.navigate(StatsFragmentDirections.actionStatsFragmentToChartFragment())
        }
    }
}