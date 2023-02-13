package com.example.earsensei.quiz.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earsensei.MusicElement
import com.example.earsensei.MusicElements
import com.example.earsensei.NotesPlayer
import com.example.earsensei.QuizType
import com.example.earsensei.database.Answer
import com.example.earsensei.database.quizResult.QuizResult
import com.example.earsensei.database.quizResult.QuizResultDao
import com.example.earsensei.database.unlockedQuestion.UnlockedQuestion
import com.example.earsensei.database.unlockedQuestion.UnlockedQuestionDao
import com.example.earsensei.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class QuizViewModel(
    private val quizResultDao: QuizResultDao,
    private val unlockedQuestionDao: UnlockedQuestionDao,
    private val notesPlayer: NotesPlayer,
    private val musicElements: MusicElements,
    private val quizType: QuizType,
) : ViewModel() {

    val currentProgress = MutableLiveData(-1)
    val maximumProgress = MutableLiveData(0)
    val answers = MutableLiveData<List<Answer>>()
    val isAnswered = MutableLiveData(false)
    val isNextButtonVisible = MutableLiveData(false)
    val isLoading = MutableLiveData(true)
    val goBack = SingleLiveEvent<Boolean>()
    val toastEvent: SingleLiveEvent<String> = SingleLiveEvent()

    init {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val unlockedQuestions = unlockedQuestionDao.getByType(quizType.name)
            val musicElementList = unlockedQuestions.toMusicElementList()
            val musicElementListSorted = musicElementList.sortedBy { it.order }
            val answerList = generateQuestions(musicElementListSorted)
            answers.postValue(answerList.first())
            isLoading.postValue(false)
        }
    }

    private fun generateQuestions(musicElementList: List<MusicElement>): List<List<Answer>> {
        val correctAnswer = musicElementList.random()
        val baseNote = correctAnswer.getRange().entries.random()
        val answerList = musicElementList.map { musicElement ->
            Answer(
                musicElement.name,
                correctAnswer.name,
                musicElement.translation,
                baseNote.key,
                musicElement.name == correctAnswer.name,
                false,
            )
        }
        return listOf(answerList)
    }

    private fun iterateQuiz() {
        currentProgress.postValue(currentProgress.value?.inc())
        setNotes()
        playNotes()
    }

    private fun setNotes() {

    }


    private fun setQuestionPool() {
        viewModelScope.launch(Dispatchers.IO) {
            iterateQuiz()
        }
    }

    fun playNotes() {
        notesPlayer.playMultipleNotes()
    }

    private fun List<UnlockedQuestion>.toMusicElementList(): List<MusicElement> {
        val unlockedMusicElements = mapNotNull { unlockedQuestion ->
            musicElements.musicList.first { it.name == unlockedQuestion.name }
        }
        return unlockedMusicElements
    }

    fun onAnswerClick(answer: Answer) {
        isAnswered.postValue(true)
        highlightAnswers()
        addResult(answer)
        iterateQuiz()
    }

    private fun highlightAnswers() {
        val newAnswers = answers.value?.map { it.copy(isHighlighted = true) } ?: listOf()
        answers.postValue(newAnswers)
    }

    private fun addResult(answer: Answer) {
        val quizResult = QuizResult(
            type = quizType.name,
            baseNote = answer.baseNote,
            correctAnswer = answer.correctAnswer,
            userAnswer = answer.name,
        )
        println("cos1 ${quizResult.toString()}")
        viewModelScope.launch(Dispatchers.IO) {
            quizResultDao.insert(quizResult)
        }
    }


    fun nextQuiz() {
        isNextButtonVisible.postValue(false)
        iterateQuiz()
    }

    private fun canUnlockNewQuestion(results: List<QuizResult>): Boolean =
        results.all { it.isCorrect } && results.size == LIMIT

    companion object {
        const val LIMIT = 2
        const val MIN_UNLOCKED_QUESTIONS = 2
    }
}

