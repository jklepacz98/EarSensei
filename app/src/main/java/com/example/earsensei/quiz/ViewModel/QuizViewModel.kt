package com.example.earsensei.quiz.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    val maximumProgress = MutableLiveData(20)
    val answers = MutableLiveData<List<Answer>>()
    val isAnswered = MutableLiveData(false)
    val isNextButtonVisible = MutableLiveData(false)
    val isLoading = MutableLiveData(true)
    val goBack = SingleLiveEvent<Boolean>()
    val toastEvent: SingleLiveEvent<String> = SingleLiveEvent()

    init {
        isLoading.postValue(true)
        viewModelScope.launch {
            val unlockedQuestions = unlockedQuestionDao.getByType(quizType.name)
            val newAnswers = unlockedQuestions.toAnswers()
            answers.postValue(newAnswers)
            isLoading.postValue(false)
        }
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

    private fun List<UnlockedQuestion>.toAnswers(): List<Answer> {
        val correctAnswer = this.random()
        return map { unlockedQuestion ->
            val interval = musicElements.musicList.first { it.name == unlockedQuestion.name }
            Answer(interval.name,
                correctAnswer.name,
                interval.translation,
                interval.name == correctAnswer.name,
                true)
        }
    }

    fun onAnswerClick(answer: Answer) {
        highlightAnswers()
        addResult(answer)
        iterateQuiz()
    }

    private fun highlightAnswers() {
        val newAnswers = answers.value ?: listOf()
        newAnswers.forEach { it.isHighlighted = true }
        answers.postValue(newAnswers)
    }

    private fun addResult(answer: Answer) {
        //todo
//        val quizResult = QuizResult(
//            type = quizType.name,
//            baseNote = ,
//            correctAnswer = answer.name,
//            userAnswer = answer.correctAnswer,
//        )
//        quizResultDao.insert(quizResult)
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

