package com.example.earsensei.musictheory.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.earsensei.R
import com.example.earsensei.databinding.FragmentMusicTheoryChordsBinding

class MusicTheoryChordsFragment : Fragment() {
    private lateinit var binding: FragmentMusicTheoryChordsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMusicTheoryChordsBinding.inflate(layoutInflater)
        binding.tvDescription.text = resources.getText(R.string.music_theory_chords)
        return binding.root
    }
}