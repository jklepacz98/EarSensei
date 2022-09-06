package com.example.earsensei.intervalsquiz.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.earsensei.*
import com.example.earsensei.database.Answer
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.database.Quiz
import com.example.earsensei.database.progression.Progression
import com.example.earsensei.database.quizResult.QuizResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class IntervalsQuizViewModel(app: Application) : AndroidViewModel(app) {

    private val db: EarSenseiDatabase by lazy { EarSenseiDatabase.getDataBase(app) }
    private val notesPlayer: NotesPlayer by lazy { NotesPlayer(app) }
    private val quizGenerator: QuizGenerator by lazy { QuizGenerator(db, CHORDS.AUGMENTED) }
    private lateinit var progressions: List<Progression>
    private val quizes: List<Quiz> by lazy { quizGenerator.generateQuizes(INTERVALS.getType()) }
    private val quizIterator: Iterator<Quiz> by lazy { quizes.iterator() }
    private lateinit var curentQuiz: Quiz
    val progress: MutableLiveData<Int> = MutableLiveData<Int>(-1)
    val progressMax: MutableLiveData<Int> = MutableLiveData<Int>(20)
    val answers: MutableLiveData<List<Answer>> = MutableLiveData<List<Answer>>()
    val isAnswered: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val notes: MutableLiveData<List<Note>> = MutableLiveData()
    val isNextButtonVisible: MutableLiveData<Boolean> = MutableLiveData(false)
    val goBack: MutableLiveData<Boolean> = MutableLiveData(false)

    init {
        setProgression()
    }

    private fun iterateQuiz() {
        progress.postValue(progress.value?.inc())
        if (quizIterator.hasNext()) {
            curentQuiz = quizIterator.next()
            answers.postValue(curentQuiz.answers)
            setNotes()
            playNotes()
        } else {
            goBack.postValue(true)
        }
    }

    fun setNotes() {
        val baseNote = Note.NOTES[NOTES_WITH_OCTAVE[curentQuiz.baseNote]]
        val sum =
            NOTES_WITH_OCTAVE[curentQuiz.baseNote]!! + (INTERVALS.valueOf(curentQuiz.correctAnswer.name)).halfSteps
        val secondNote = Note.NOTES[sum]
        Log.d("cos1234", baseNote.toString())
        Log.d("cos12345", sum.toString())
        notesPlayer.setNotes(listOf(baseNote!!, secondNote!!))
    }


    private fun setProgression() {
        viewModelScope.launch(Dispatchers.IO) {
            progressions = db.progressionDao().getAllData()
            iterateQuiz()
            progressMax.postValue(quizes.size)
        }
    }

    fun playNotes() {
        notesPlayer.playMultipleNotes()
    }

    private fun List<Progression>.toAnswers(): List<Answer> =
        map {
            val interval = INTERVALS.valueOf(it.question)
            Answer(interval.name, interval.translation, false)
        }.also { it.random().isCorrect = true }

    fun onAnswerClick(position: Int) {
        highlightAnswers()
        Log.d("cos1", answers.value!![position].toString())
        addResult(answers.value!![position])
        isNextButtonVisible.postValue(true)
    }

    fun highlightAnswers() {
        val newAnswers = answers.value
        newAnswers?.forEach {
            it.isHighlighted = true
        }
        answers.postValue(newAnswers ?: listOf())
    }

    fun addResult(answer: Answer) {
        Log.d("cos3", curentQuiz.correctAnswer.name.toString())
        Log.d("cos4", answer.name.toString())
        val quizResult = QuizResult(
            quizType = INTERVALS.getType(),
            baseNote = curentQuiz.baseNote,
            correctAnswer = curentQuiz.correctAnswer.name.toString(),
            userAnswer = answer.name.toString()

        )
        viewModelScope.launch(Dispatchers.IO) {
            db.resultDao().insert(quizResult)
        }
    }

    fun nextQuiz() {
        isNextButtonVisible.postValue(false)
        iterateQuiz()
    }

}

