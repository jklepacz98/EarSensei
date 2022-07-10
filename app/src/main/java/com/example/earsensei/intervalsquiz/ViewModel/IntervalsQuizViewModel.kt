package com.example.earsensei.intervalsquiz.ViewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.earsensei.*
import com.example.earsensei.dbmodels.QuizRecordModel
import com.example.earsensei.intervalsquiz.Model.AnswerModel
import java.util.*
import kotlin.collections.ArrayList


class IntervalsQuizViewModel(application: Application) : AndroidViewModel(application) {

    val progress: MutableLiveData<Int> = MutableLiveData<Int>(0)
    val progressMax: MutableLiveData<Int> = MutableLiveData<Int>(20)
    val answers: MutableLiveData<ArrayList<AnswerModel>> = MutableLiveData<ArrayList<AnswerModel>>()
    val toastMessage: MutableLiveData<String> = MutableLiveData<String>()
    val state: MutableLiveData<String> = MutableLiveData<String>(QuizState.UNANSWERED)
    val earSenseiDBHelper: EarSenseiDBHelper = EarSenseiDBHelper(application.applicationContext)
    val intervalProgress: ArrayList<ProgressModel> = arrayListOf()

    var quizModel: MutableLiveData<QuizModel> = MutableLiveData(generateQuizModel())

    init {
        quizModel.value = generateQuizModel()
        setAnswers(generateAnswers())
    }

    fun getIntervalRecordsFromDB(): ArrayList<QuizRecordModel> {
        return ArrayList(earSenseiDBHelper.readAllQuizData()
            .filter { it.quizType == QuizType.INTERVALS })
    }

    fun getIntervalProgressFromDB(): ArrayList<ProgressModel> {
        return ArrayList(earSenseiDBHelper.readAllProgressData()
            .filter { it.type == QuizType.INTERVALS })
    }

    fun generateQuizModel(): QuizModel {

        //todo
        val interval = getIntervalProgressFromDB().map { it.question }.random()
        val notes = MusicTerminology.notesWithOctave
        val filteredNotes = notes.filter { it.value < notes.size - (MusicTerminology.intervals[interval] ?: 0)}
        val baseNote = filteredNotes.keys.random()
        val questionPool = getIntervalProgressFromDB().map { it.question }
        //todo
        return QuizModel(QuizType.INTERVALS ,baseNote, interval, questionPool)
    }

    fun checkAnswer(answerModel: AnswerModel) {
        if (answerModel.name == quizModel.value?.correctAnswer) {
            state.value = QuizState.ANSWERED_CORRECT
        } else {
            state.value = QuizState.ANSWERED_WRONG
        }
        if (state.value == QuizState.UNANSWERED){
            earSenseiDBHelper.addQuizContentValues(
                earSenseiDBHelper.createQuizContentValues(
                    quizModel.value?.baseNote ?: "error",
                    quizModel.value?.quizType ?: "error",
                    quizModel.value?.correctAnswer ?: "error",
                    answerModel.name,
                    Date().time
                )
            )
        }
    }

    fun generateAnswers(): ArrayList<AnswerModel> {
        val answers: ArrayList<AnswerModel> = arrayListOf()
        quizModel.value?.questionPool?.forEach {
            answers.add(
                AnswerModel(
                    it,
                    MusicTerminology.Translations.intervals[it] ?: R.string.is_null
                )
            )
        }
        return answers
    }

    fun setAnswers(answers: ArrayList<AnswerModel>) {
        this.answers.value = answers
    }

    fun getNotes(): List<Note> {
        val notes: MutableList<Note> = mutableListOf()
        val notesInt = quizModel.value?.let { QuizNotesGenerator.generateIntervalsNotes(it) }
        notesInt?.forEach{
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
}

