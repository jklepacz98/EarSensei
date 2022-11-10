package com.example.earsensei.intervalsquiz.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earsensei.MusicTerminology
import com.example.earsensei.NOTES_WITH_OCTAVE
import com.example.earsensei.NotesPlayer
import com.example.earsensei.QuizGenerator
import com.example.earsensei.database.Answer
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.database.Quiz
import com.example.earsensei.database.quizResult.QuizResult
import com.example.earsensei.database.unlockedQuestion.UnlockedQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class QuizViewModel(
    val db: EarSenseiDatabase,
    val notesPlayer: NotesPlayer,
    private val musicTerminology: MusicTerminology
) : ViewModel() {

    private val quizGenerator: QuizGenerator by lazy { QuizGenerator(db) }

    private lateinit var unlockedQuestions: List<UnlockedQuestion>
    private val quizes: List<Quiz> by lazy { quizGenerator.generateQuizes(musicTerminology) }
    private val quizIterator: Iterator<Quiz> by lazy { quizes.iterator() }
    private lateinit var curentQuiz: Quiz
    val progress = MutableLiveData<Int>(-1)
    val progressMax = MutableLiveData<Int>(20)
    val answers = MutableLiveData<List<Answer>>()
    val isAnswered = MutableLiveData(false)
    val isNextButtonVisible = MutableLiveData(false)
    val goBack = MutableLiveData(false)
    val lastUnlockQuestionDate by lazy { db.unlockedQuestionDao().getLatest(musicTerminology.type) }
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
            //todo Do something more. Maybe some gratulations?
            goBack.postValue(true)
        }
    }

    fun setNotes() {
        val baseNote = NOTES_WITH_OCTAVE[curentQuiz.baseNote]!!
        val interval = musicTerminology.musicMap.get(curentQuiz.correctAnswer.name)
        val noteIndices = interval!!.toNoteIndices(baseNote)
        notesPlayer.setNotes(noteIndices)
    }


    private fun setQuestionPool() {
        viewModelScope.launch(Dispatchers.IO) {
            unlockedQuestions = db.unlockedQuestionDao().getAllData()
            iterateQuiz()
            lastRecords =
                db.resultDao().getQuizResult(musicTerminology.type, lastUnlockQuestionDate, LIMIT)
                    .toMutableList()
            progressMax.postValue(quizes.size)
        }
    }

    fun playNotes() {
        notesPlayer.playMultipleNotes()
    }

    private fun List<UnlockedQuestion>.toAnswers(): List<Answer> =
        map {
            val interval = musicTerminology.musicMap.get(it.question)!!
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
                    //unlockedQuestions = db.unlockedquestionDao().getByType(Intervals.getType()).sortedBy { Intervals.valueOf(it.question).halfSteps }
                    val lockedQuestions = musicTerminology.list
                        .map { it.name }
                        //todo list of
                        .minus(unlockedQuestions.map { it.question })
                    if (lockedQuestions.isNotEmpty()) {
                        val randomQuestion = lockedQuestions.random()
                        makeToast.postValue(randomQuestion + " unlocked")
                        db.unlockedQuestionDao().insert(
                            UnlockedQuestion(
                                question = randomQuestion,
                                type = musicTerminology.type
                            )
                        )
                    }
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
            quizType = musicTerminology.type,
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

