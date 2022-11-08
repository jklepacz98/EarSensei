package com.example.earsensei

import androidx.room.Room
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.graphs.ViewModel.GraphViewModel
import com.example.earsensei.intervalsquiz.ViewModel.QuizViewModel
import com.example.earsensei.settings.ViewModel.SettingsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val appModule = module {
    viewModel { parameters -> QuizViewModel(get(), get(), get { parametersOf(parameters.get()) }) }
    viewModel { parameters -> GraphViewModel(get(), get { parametersOf(parameters.get()) }) }
    viewModel { SettingsViewModel() }

    single {
        Room.databaseBuilder(
            androidApplication(), EarSenseiDatabase::class.java, EarSenseiDatabase.DATABASE_NAME
        ).build()
    }
    single { NotesPlayer(androidApplication()) }

    factory { parameters -> MusicTerminologyFactory.get(parameters.get()) }
}