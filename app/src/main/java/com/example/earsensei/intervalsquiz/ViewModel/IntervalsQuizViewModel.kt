package com.example.earsensei.intervalsquiz.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.earsensei.Note
import com.example.earsensei.Note.Companion.NOTES
import com.example.earsensei.database.Answer
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.database.progression.Progression
import com.example.earsensei.database.quizResult.QuizResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class IntervalsQuizViewModel(app: Application) : AndroidViewModel(app) {

    var progressions: List<Progression> = listOf()
    val progress: MutableLiveData<Int> = MutableLiveData<Int>(0)
    val progressMax: MutableLiveData<Int> = MutableLiveData<Int>(20)
    val answers: MutableLiveData<List<Answer>> = MutableLiveData<List<Answer>>()
    val isAnswered: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var notes: MutableLiveData<List<Note>> = MutableLiveData()
    val db: EarSenseiDatabase by lazy { EarSenseiDatabase.getDataBase(app) }


    init {
        viewModelScope.launch(Dispatchers.IO) {
            progressions = db.progressionDao().readAllData()
        }
        progressions = listOf()
        //setAnswers(generateAnswers())
        notes.value = NOTES.values.toList()
    }


    fun addResult(quizResult: QuizResult) {
        viewModelScope.launch(Dispatchers.IO) {
            db.resultDao().insert(quizResult)
        }
    }

}

