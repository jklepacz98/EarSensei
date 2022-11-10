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

val viewModelModule = module {
    viewModel { (type: String) -> QuizViewModel(get(), get(), get { parametersOf(type) }) }
    viewModel { (type: String) -> GraphViewModel(get(), get { parametersOf(type) }) }
    viewModel { SettingsViewModel() }
}
val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(), EarSenseiDatabase::class.java, EarSenseiDatabase.DATABASE_NAME
        ).build()
    }
    single {
        val database = get<EarSenseiDatabase>()
        database.resultDao()
    }
    single {
        val database = get<EarSenseiDatabase>()
        database.unlockedQuestionDao()
    }
}
val notesPlayerModule = module {
    single { NotesPlayer(androidApplication()) }
    factory { parameters -> MusicTerminologyFactory.get(parameters.get()) }
}