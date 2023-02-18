package com.example.earsensei

import androidx.room.Room
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.graphs.ViewModel.GraphViewModel
import com.example.earsensei.quiz.ViewModel.QuizViewModel
import com.example.earsensei.settings.ViewModel.SettingsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { (type: MusicElementType) ->
        QuizViewModel(get(), get(), get(), get { parametersOf(type) }, type)
    }
    viewModel { (type: MusicElementType) -> GraphViewModel(get(), get { parametersOf(type) }) }
    viewModel { SettingsViewModel() }
}
val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            EarSenseiDatabase::class.java,
            EarSenseiDatabase.DATABASE_NAME,
        ).createFromAsset("database/PrepopulateEarSensei.db").build()
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
    factory { NotesPlayer(androidApplication()) }
    factory { parameters -> MusicElementsFactory.get(parameters.get()) }
}
val othersModule = module {
    factory { (musicElementType: MusicElementType) -> MusicElementsFactory.get(musicElementType) }
}