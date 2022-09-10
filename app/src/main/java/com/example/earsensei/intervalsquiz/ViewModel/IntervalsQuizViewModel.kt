package com.example.earsensei.intervalsquiz.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.earsensei.*
import com.example.earsensei.database.Answer
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.database.Quiz
import com.example.earsensei.database.quizResult.QuizResult
import com.example.earsensei.database.unlockedQuestion.UnlockedQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class IntervalsQuizViewModel(app: Application) : AndroidViewModel(app) {

    private val db: EarSenseiDatabase by lazy { EarSenseiDatabase.getDataBase(app) }
    private val notesPlayer: NotesPlayer by lazy { NotesPlayer(app) }
    private val quizGenerator: QuizGenerator by lazy { QuizGenerator(db) }

    private lateinit var unlockedQuestions: List<UnlockedQuestion>
    private val quizes: List<Quiz> by lazy { quizGenerator.generateQuizes(INTERVALS.getType()) }
    private val quizIterator: Iterator<Quiz> by lazy { quizes.iterator() }
    private lateinit var curentQuiz: Quiz
    val progress = MutableLiveData<Int>(0)
    val progressMax = MutableLiveData<Int>(20)
    val answers = MutableLiveData<List<Answer>>()
    val isAnswered = MutableLiveData(false)
    val isNextButtonVisible = MutableLiveData(false)
    val goBack = MutableLiveData(false)
    val lastUnlockQuestionDate by lazy { db.unlockedquestionDao().getLatest(INTERVALS.getType()) }
    lateinit var lastRecords: MutableList<QuizResult>

    val makeToast: MutableLiveData<String> = MutableLiveData()

    init {
        setQuestionPool()
    }

    private fun iterateQuiz() {
        progress.postValue(progress.value?.inc())
        if (quizIterator.hasNext()) {
            curentQuiz = quizIterator.next()
            answers.postValue(curentQuiz.answers)
            setNotes()
            playNotes()
        } else {
            //todo Do something more. Maybe some congratulations?
            goBack.postValue(true)
        }
    }

    fun setNotes() {
        val baseNote = Note.NOTES[NOTES_WITH_OCTAVE[curentQuiz.baseNote]]
        val sum =
            NOTES_WITH_OCTAVE[curentQuiz.baseNote]!! + (INTERVALS.valueOf(curentQuiz.correctAnswer.name)).halfSteps
        val secondNote = Note.NOTES[sum]
        notesPlayer.setNotes(listOf(baseNote!!, secondNote!!))
    }


    private fun setQuestionPool() {
        viewModelScope.launch(Dispatchers.IO) {
            unlockedQuestions = db.unlockedquestionDao().getAllData()
            iterateQuiz()
            lastRecords = db.resultDao().get(INTERVALS.getType(), lastUnlockQuestionDate, LIMIT)
                .toMutableList()
            progressMax.postValue(quizes.size)
        }
    }

    fun playNotes() {
        notesPlayer.playMultipleNotes()
    }

    private fun List<UnlockedQuestion>.toAnswers(): List<Answer> =
        map {
            val interval = INTERVALS.valueOf(it.question)
            Answer(interval.name, interval.translation, false)
        }.also { it.random().isCorrect = true }

    fun onAnswerClick(position: Int) {
        if (curentQuiz.isAnswered == false) {
            highlightAnswers()
            addResult(answers.value!![position])
            isNextButtonVisible.postValue(true)
            viewModelScope.launch(Dispatchers.IO) {
                if (canUnlockNewQuestion(lastRecords)) {
                    lastRecords.clear()
                    //unlockedQuestions = db.unlockedquestionDao().getByType(INTERVALS.getType()).sortedBy { INTERVALS.valueOf(it.question).halfSteps }
                    val lockedQuestions = INTERVALS.values()
                        .toList()
                        .map { it.name }
                        //todo list of
                        .minus(unlockedQuestions.map { it.question })
                    val randomQuestion = lockedQuestions.random()
                    makeToast.postValue(randomQuestion + " unlocked")
                    db.unlockedquestionDao().insert(
                        UnlockedQuestion(
                            question = randomQuestion,
                            type = INTERVALS.getType()
                        )
                    )
                }
            }
        }
        curentQuiz.isAnswered = true
    }

    fun highlightAnswers() {
        val newAnswers = answers.value
        newAnswers?.forEach {
            it.isHighlighted = true
        }
        answers.postValue(newAnswers ?: listOf())
    }

    fun addResult(answer: Answer) {
        val quizResult = QuizResult(
            quizType = INTERVALS.getType(),
            baseNote = curentQuiz.baseNote,
            correctAnswer = curentQuiz.correctAnswer.name,
            userAnswer = answer.name
        )

        lastRecords.add(quizResult)
        lastRecords = lastRecords.takeLast(LIMIT).toMutableList()
        viewModelScope.launch(Dispatchers.IO) {
            db.resultDao().insert(quizResult)
        }
    }

    fun nextQuiz() {
        isNextButtonVisible.postValue(false)
        iterateQuiz()
    }

    private fun canUnlockNewQuestion(results: List<QuizResult>): Boolean {
        return results.all { it.isCorrect } && results.size == LIMIT
    }

    companion object {
        val LIMIT = 2
    }
}

