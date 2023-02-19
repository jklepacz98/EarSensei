package com.example.earsensei.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.earsensei.databinding.FragmentMusicTheoryMenuBinding
import com.example.earsensei.utils.safeNavigate

class MusicTheoryMenuFragment : Fragment() {
    private lateinit var binding: FragmentMusicTheoryMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMusicTheoryMenuBinding.inflate(layoutInflater)
        setupButtons()
        return binding.root
    }

    private fun setupButtons() {
        binding.run {
//            buttonBasics.setOnClickListener {
//                safeNavigate(MusicTheoryMenuFragmentDirections.actionMusicTheoryMenuFragmentToMusicTheoryBasicsFragment())
//            }
            buttonIntervals.setOnClickListener {
                safeNavigate(MusicTheoryMenuFragmentDirections.actionMusicTheoryMenuFragmentToMusicTheoryIntervalsFragment())
            }
            buttonChords.setOnClickListener {
                safeNavigate(MusicTheoryMenuFragmentDirections.actionMusicTheoryMenuFragmentToMusicTheoryChordsFragment())
            }
            buttonScales.setOnClickListener {
                safeNavigate(MusicTheoryMenuFragmentDirections.actionMusicTheoryMenuFragmentToMusicTheoryScalesFragment())
            }
        }
    }
}