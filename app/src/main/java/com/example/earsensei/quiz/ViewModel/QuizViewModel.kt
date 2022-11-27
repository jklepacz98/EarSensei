package com.example.earsensei.quiz.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earsensei.MusicTerminology
import com.example.earsensei.NotesPlayer
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
    private val musicTerminology: MusicTerminology
) : ViewModel() {

    val currentProgress = MutableLiveData(-1)
    val maximumProgress = MutableLiveData(20)
    val answers = MutableLiveData<List<Answer>>()
    val isAnswered = MutableLiveData(false)
    val isNextButtonVisible = MutableLiveData(false)
    val goBack = SingleLiveEvent<Boolean>()
    val toastEvent: SingleLiveEvent<String> = SingleLiveEvent()

    init {
        setQuestionPool()
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

    private fun List<UnlockedQuestion>.toAnswers(): List<Answer> =
        map { unlockedQuestion ->
            val interval = musicTerminology.musicList.first { it.name == unlockedQuestion.question }
            Answer(interval.name, interval.translation, false)
        }.also { it.random().isCorrect = true }

    fun onAnswerClick(answer: Answer) {

    }

    private fun highlightAnswers() {
        val newAnswers = answers.value
        newAnswers?.forEach { it.isHighlighted = true }
        answers.postValue(newAnswers ?: listOf())
    }

    private fun addResult(answerName: String) {

    }


    fun nextQuiz() {
        isNextButtonVisible.postValue(false)
        iterateQuiz()
    }

    private fun canUnlockNewQuestion(results: List<QuizResult>): Boolean {
        return results.all { it.isCorrect } && results.size == LIMIT
    }

    companion object {
        const val LIMIT = 2
    }
}

