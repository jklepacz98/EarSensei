package com.example.earsensei

import com.example.earsensei.graphs.ViewModel.GraphViewModel
import com.example.earsensei.intervalsquiz.ViewModel.QuizViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { parameters -> QuizViewModel(androidApplication(), parameters.get()) }
    viewModel { parameters -> GraphViewModel(androidApplication(), parameters.get()) }
}