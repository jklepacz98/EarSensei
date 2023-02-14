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
import com.example.earsensei.quiz.QuizQuestion
import com.example.earsensei.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


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
    val toastEvent: SingleLiveEvent<Int> = SingleLiveEvent()
    var quizQuestions: Queue<QuizQuestion> = LinkedList()

    init {
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val unlockedQuestions = unlockedQuestionDao.getByType(quizType.name)
            val musicElementList = unlockedQuestions.toMusicElementList()
            val musicElementListSorted = musicElementList.sortedBy { it.order }
            //todo
            quizQuestions = generateQuestions(musicElementListSorted, 3, 3)
            maximumProgress.postValue(quizQuestions.size)
            iterateQuiz()
            isLoading.postValue(false)
        }
    }

    private suspend fun generateQuestions(
        musicElementList: List<MusicElement>,
        numberOfQuestions: Int,
        numberOfQuestions2: Int,
    ): Queue<QuizQuestion> {
        val quizQuestions: Queue<QuizQuestion> = LinkedList()
        for (i in 0..numberOfQuestions) {
            val correctAnswer = musicElementList.random()
            val baseNote = correctAnswer.getRange().entries.random()
            val answerList = musicElementList.map { musicElement ->
                Answer(
                    musicElement.name,
                    correctAnswer.name,
                    musicElement.stringResourceId,
                    baseNote.key,
                    musicElement.name == correctAnswer.name,
                    false,
                )
            }
            val quizQuestion = QuizQuestion(answerList, correctAnswer, baseNote.value)
            quizQuestions.add(quizQuestion)
        }
        val worstRecord1 = getWorstRecord(quizType.name)
        val worstRecord2 = getMostCommonMistake(quizType.name, worstRecord1)
        val musicElementListWorst =
            musicElementList.filter { it.name == worstRecord1 || it.name == worstRecord2 }
        for (i in 0..numberOfQuestions2) {
            val correctAnswer = musicElementListWorst.random()
            val baseNote = correctAnswer.getRange().entries.random()
            val answerList = musicElementListWorst.map { musicElement ->
                Answer(
                    musicElement.name,
                    correctAnswer.name,
                    musicElement.stringResourceId,
                    baseNote.key,
                    musicElement.name == correctAnswer.name,
                    false,
                )
            }
            val quizQuestion = QuizQuestion(answerList, correctAnswer, baseNote.value)
            quizQuestions.add(quizQuestion)
        }
        return quizQuestions
    }

    private fun iterateQuiz() {
        if (quizQuestions.isNotEmpty()) {
            isAnswered.postValue(false)
            val newQuizQuestion = quizQuestions.first()
            answers.postValue(newQuizQuestion.answers)
            quizQuestions.remove()
            currentProgress.postValue(currentProgress.value?.inc())
            setNotes(newQuizQuestion.correctAnswer.toNoteIds(newQuizQuestion.baseNote))
            playNotes()
        } else {
            goBack.call()
        }
    }

    private fun setNotes(noteIds: List<Int>) {
        notesPlayer.setNotes(noteIds)
    }

    fun playNotes() {
        notesPlayer.playMultipleNotes(viewModelScope)
    }

    private fun List<UnlockedQuestion>.toMusicElementList(): List<MusicElement> {
        val unlockedMusicElements = mapNotNull { unlockedQuestion ->
            musicElements.musicList.first { it.name == unlockedQuestion.name }
        }
        return unlockedMusicElements
    }

    fun onAnswerClick(answer: Answer) {
        if (isAnswered.value != true) {
            isAnswered.postValue(true)
            highlightAnswers()
            viewModelScope.launch(Dispatchers.IO) {
                addResult(answer)
                val type = musicElements.quizType.name
                val latestUnlockDate = unlockedQuestionDao.getLatestDate(quizType.name)
                val userAnswers = quizResultDao.getUserAnswer(type).sortedBy { it.date }
                    .filter { latestUnlockDate < it.date }.takeLast(LIMIT)
                if (canUnlockNewQuestion(userAnswers)) {
                    addUnlockedQuestion()
                }
            }
        }
    }

    private fun highlightAnswers() {
        val newAnswers = answers.value?.map { it.copy(isHighlighted = true) } ?: listOf()
        answers.postValue(newAnswers)
    }

    private suspend fun addUnlockedQuestion() {
        val unlockedQuestionNames = unlockedQuestionDao.getByType(quizType.name).map { it.name }
        val filteredMusicElements =
            musicElements.musicList.filter { it.name !in unlockedQuestionNames }
                .sortedBy { it.order }
        if (filteredMusicElements.isNotEmpty()) {
            println()
            val unlockedMusicElement = filteredMusicElements.first()
            val unlockedQuestion =
                UnlockedQuestion(type = quizType.name, name = unlockedMusicElement.name)
            unlockedQuestionDao.insert(unlockedQuestion)
            toastEvent.postValue(unlockedMusicElement.stringResourceId)
        }
    }

    private suspend fun addResult(answer: Answer) {
        val quizResult = QuizResult(
            type = quizType.name,
            baseNote = answer.baseNote,
            correctAnswer = answer.correctAnswer,
            userAnswer = answer.name,
        )
        quizResultDao.insert(quizResult)

    }


    fun nextQuiz() {
        isNextButtonVisible.postValue(false)
        iterateQuiz()
    }

    private fun canUnlockNewQuestion(results: List<QuizResult>): Boolean =
        results.all { it.isCorrect } && results.size == LIMIT

    companion object {
        const val LIMIT = 10
        //const val MIN_UNLOCKED_QUESTIONS = 2
    }


    suspend fun getWorstRecord(type: String): String {
        val unlockedQuestions = unlockedQuestionDao.getByType(type)
        val progressionsRatios = mutableMapOf<String, Float>()
        unlockedQuestions.forEach {
            val dividend = quizResultDao.getCountCorrect(it.type, it.name)
            val divider = quizResultDao.getCount(it.type, it.name)
            val result = dividend.toFloat() / divider.toFloat()
            progressionsRatios.put(it.name, result)
        }
        val worst =
            progressionsRatios.minByOrNull { it.value }?.key ?: unlockedQuestions.first().name
        return worst
    }

    suspend fun getMostCommonMistake(type: String, correctAnswer: String): String {
        val unlockedQuestions = unlockedQuestionDao.getByType(type).map { it.name }
        val userAnswer = quizResultDao.getUserAnswer(type, correctAnswer)
            .filter { userAnswer -> unlockedQuestions.contains(userAnswer) }
        val map = mutableMapOf<String, Long>()
        userAnswer.forEach {
            val count = quizResultDao.getCount(type, it, correctAnswer)
            map.put(it, count)
        }
        return map.maxByOrNull { it.value }?.key ?: unlockedQuestions.last()
    }

    override fun onCleared() {
        notesPlayer.clear()
        super.onCleared()
    }
}

