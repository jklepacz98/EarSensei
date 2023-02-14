package com.example.earsensei.musictheory.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.earsensei.R
import com.example.earsensei.databinding.FragmentMusicTheoryIntervalsBinding

class MusicTheoryIntervalsFragment : Fragment() {
    private lateinit var binding: FragmentMusicTheoryIntervalsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMusicTheoryIntervalsBinding.inflate(layoutInflater)
        binding.tvDescription.text = resources.getText(R.string.music_theory_intervals)
        return binding.root
    }
}