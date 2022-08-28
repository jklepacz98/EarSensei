package com.example.earsensei.intervalsquiz.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.earsensei.INTERVALS
import com.example.earsensei.Note
import com.example.earsensei.database.AnswerButtonModel
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.database.progression.Progression
import com.example.earsensei.database.quizResult.QuizResult
import com.example.earsensei.logHelp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class IntervalsQuizViewModel(app: Application) : AndroidViewModel(app) {

    private val db: EarSenseiDatabase by lazy { EarSenseiDatabase.getDataBase(app) }
    private lateinit var progressions: List<Progression>
    val progress: MutableLiveData<Int> = MutableLiveData<Int>(0)
    val progressMax: MutableLiveData<Int> = MutableLiveData<Int>(20)
    val answers: MutableLiveData<List<AnswerButtonModel>> =
        MutableLiveData<List<AnswerButtonModel>>()
    val isAnswered: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val areAnswersHighlighted: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val notes: MutableLiveData<List<Note>> = MutableLiveData()

    init {
        setProgression()
    }

    private fun setProgression() {
        viewModelScope.launch(Dispatchers.IO) {
            progressions = db.progressionDao().getAllData().also { logHelp(1, it.toString()) }
            answers.postValue(generateAnswers())
        }
    }

    private fun generateAnswers(): List<AnswerButtonModel> = progressions.toAnswers()

    private fun List<Progression>.toAnswers(): List<AnswerButtonModel> =
        map {
            val interval = INTERVALS.valueOf(it.question)
            AnswerButtonModel(interval.name, interval.translation, false)
        }.also { logHelp(4, it.toString()) }

    fun answerClicked() {
        areAnswersHighlighted.postValue(true)
        isAnswered.postValue(true)
    }

    fun nextQuiz() {
        progress.value?.inc()
        areAnswersHighlighted.postValue(false)
        isAnswered.postValue(false)
    }

    fun checkAnswer(position: Int) {

    }

    fun addResult(quizResult: QuizResult) {
        viewModelScope.launch(Dispatchers.IO) {
            db.resultDao().insert(quizResult)
        }
    }

}

