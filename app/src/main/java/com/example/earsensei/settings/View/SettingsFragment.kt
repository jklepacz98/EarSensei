package com.example.earsensei.settings.View

import androidx.fragment.app.Fragment
import com.example.earsensei.settings.ViewModel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment() {

    private val viewModel by viewModel<SettingsViewModel>()
    
}