package com.example.earsensei.intervalsquiz.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.earsensei.*
import com.example.earsensei.database.Answer
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.database.progress.Progress
import com.example.earsensei.database.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class IntervalsQuizViewModel(app: Application) : AndroidViewModel(app) {

    val progress: MutableLiveData<Int> = MutableLiveData<Int>(0)
    val progressMax: MutableLiveData<Int> = MutableLiveData<Int>(20)
    val answers: MutableLiveData<List<Answer>> = MutableLiveData<List<Answer>>()
    val toastMessage: MutableLiveData<String> = MutableLiveData<String>()
    val state: MutableLiveData<Int> = MutableLiveData<Int>(QuizState.UNANSWERED)
    val intervalProgress: List<Progress> = listOf()

    var quizModel: MutableLiveData<QuizModel> = MutableLiveData(generateQuizModel())

    //todo why it doesn't work with lazy
    val db by lazy {
        Room.databaseBuilder(
            getApplication<Application>().applicationContext,
            EarSenseiDatabase::class.java, "EarSenseiDB"
        ).build()
    }

    init {
        quizModel.value = generateQuizModel()
        setAnswers(generateAnswers())
    }

    fun showToast() {
        val msg = db.userDao().readAllData().value?.map { it.baseNote }.toString()
        toastMessage.postValue(msg)
    }

    fun addResult(result: Result) {
        viewModelScope.launch(Dispatchers.IO) {
            db.userDao().insert(result)
        }
    }

    fun generateQuizModel(): QuizModel {

        //todo
        val interval = MusicTerminology.Intervals.PERFECT_5TH
        //val interval = getIntervalProgressFromDB().map { it.question }.random()
        //?: MusicTerminology.Intervals.PERFECT_5TH
        val notes = MusicTerminology.notesWithOctave
        val filteredNotes =
            notes.filter { it.value < notes.size - (MusicTerminology.intervals[interval] ?: 0) }
        val baseNote = filteredNotes.keys.random()
        //todo

        val questionPool = listOf(interval)
        //todo
        val questionPoolUnique = questionPool.distinct()
        //todo
        return QuizModel(QuizType.INTERVALS, baseNote, interval, questionPoolUnique)
    }

    fun checkAnswer(position: Int) {
        if (state.value == QuizState.UNANSWERED) {
            state.value = QuizState.ANSWERED
            answers.value?.forEach {
                if (it.name == quizModel.value?.correctAnswer) it.state =
                    Answer.CLICKED_CORRECT
            }
            val answerModel = answers.value!!.get(position)
            if (answerModel.name == quizModel.value?.correctAnswer) {
                answers.postValue(answers.value)
            } else {
                answerModel.state = Answer.CLICKED_WRONG
                answers.postValue(answers.value)
            }
        }
    }


    fun generateAnswers(): ArrayList<Answer> {
        val answers: ArrayList<Answer> = arrayListOf()
        quizModel.value?.questionPool?.forEach {
            answers.add(
                Answer(
                    it,
                    MusicTerminology.Translations.intervals[it] ?: R.string.is_null
                )
            )
        }
        return answers
    }

    fun setAnswers(answers: ArrayList<Answer>) {
        this.answers.value = answers
    }

    fun getNotes(): List<Note> {
        val notes: MutableList<Note> = mutableListOf()
        val notesInt = quizModel.value?.let { QuizNotesGenerator.generateIntervalsNotes(it) }
        notesInt?.forEach {
            Note.notes[it]?.let { it1 -> notes.add(it1) }
        }
        return notes
    }

    fun nextQuiz() {
        progress.value = progress.value?.inc()
        state.value = QuizState.UNANSWERED
        quizModel.value = generateQuizModel()
        setAnswers(generateAnswers())
    }

    fun <T> MutableLiveData<T>.notifyObservers() {
        value = value
    }
}

