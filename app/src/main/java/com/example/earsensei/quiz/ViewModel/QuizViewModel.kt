package com.example.earsensei.quiz.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earsensei.MusicElement
import com.example.earsensei.MusicElementType
import com.example.earsensei.MusicElements
import com.example.earsensei.NotesPlayer
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
    private val musicElementType: MusicElementType,
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
            val unlockedQuestions = unlockedQuestionDao.getByType(musicElementType.name)
            val musicElementList = unlockedQuestions.toMusicElementList()
            val musicElementListSorted = musicElementList.sortedBy { it.order }
            quizQuestions = generateQuestions(
                musicElementListSorted,
                NORMAL_QUESTIONS_NUMBER,
                ADAPTIVE_QUESTIONS_NUMBER,
            )
            maximumProgress.postValue(quizQuestions.size)
            iterateQuiz()
            isLoading.postValue(false)
        }
    }

    private suspend fun generateQuestions(
        musicElementList: List<MusicElement>,
        normalQuestionsNumber: Int,
        adaptiveQuestionsNumber: Int,
    ): Queue<QuizQuestion> {
        val quizQuestions: Queue<QuizQuestion> = LinkedList()
        for (i in 0..normalQuestionsNumber) {
            quizQuestions.add(generateQuestion(musicElementList))
        }
        val worstRecord1 = getWorstRecord(musicElementType.name)
        val worstRecord2 = getMostCommonMistake(musicElementType.name, worstRecord1)
        val musicElementListWorst =
            musicElementList.filter { it.name == worstRecord1 || it.name == worstRecord2 }
        for (i in 0..adaptiveQuestionsNumber) {
            quizQuestions.add(generateQuestion(musicElementListWorst))
        }

        return quizQuestions
    }

    private fun generateQuestion(musicElementPool: List<MusicElement>): QuizQuestion {
        val correctAnswer = musicElementPool.random()
        val baseNote = correctAnswer.baseNoteRange().entries.random()
        val answerList = musicElementPool.map { musicElement ->
            Answer(
                musicElement.name,
                correctAnswer.name,
                musicElement.stringResourceId,
                baseNote.key,
                musicElement.name == correctAnswer.name,
                false,
            )
        }
        return QuizQuestion(answerList, correctAnswer, baseNote.value)
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
            musicElements.musicElementsList.first { it.name == unlockedQuestion.name }
        }
        return unlockedMusicElements
    }

    fun onAnswerClick(answer: Answer) {
        if (isAnswered.value != true) {
            isAnswered.postValue(true)
            highlightAnswers()
            viewModelScope.launch(Dispatchers.IO) {
                addResult(answer)
                val type = musicElements.musicElementType.name
                val latestUnlockDate = unlockedQuestionDao.getLatestDate(musicElementType.name)
                val userAnswers = quizResultDao.getUserAnswer(type).sortedBy { it.date }
                    .takeLast(CORRECT_QUESTIONS_NEEDED_TO_UNLOCK_NEW_QUESTION)
                    .filter { latestUnlockDate < it.date }
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
        val unlockedQuestionNames =
            unlockedQuestionDao.getByType(musicElementType.name).map { it.name }
        val filteredMusicElements =
            musicElements.musicElementsList.filter { it.name !in unlockedQuestionNames }
                .sortedBy { it.order }
        if (filteredMusicElements.isNotEmpty()) {
            println()
            val unlockedMusicElement = filteredMusicElements.first()
            val unlockedQuestion =
                UnlockedQuestion(type = musicElementType.name, name = unlockedMusicElement.name)
            unlockedQuestionDao.insert(unlockedQuestion)
            toastEvent.postValue(unlockedMusicElement.stringResourceId)
        }
    }

    private suspend fun addResult(answer: Answer) {
        val quizResult = QuizResult(
            type = musicElementType.name,
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
        results.all { it.isCorrect } && results.size == CORRECT_QUESTIONS_NEEDED_TO_UNLOCK_NEW_QUESTION

    private suspend fun getWorstRecord(type: String): String {
        val unlockedQuestions = unlockedQuestionDao.getByType(type)
        val progressionsRatios = mutableMapOf<String, Float>()
        unlockedQuestions.forEach {
            val dividend = quizResultDao.getCountCorrect(it.type, it.name)
            val divider = quizResultDao.getCount(it.type, it.name)
            val result = dividend.toFloat() / divider.toFloat()
            progressionsRatios.put(it.name, result)
        }
        return progressionsRatios.minByOrNull { it.value }?.key ?: unlockedQuestions.first().name
    }

    private suspend fun getMostCommonMistake(type: String, correctAnswer: String): String {
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

    companion object {
        const val CORRECT_QUESTIONS_NEEDED_TO_UNLOCK_NEW_QUESTION = 8
        const val NORMAL_QUESTIONS_NUMBER = 16
        const val ADAPTIVE_QUESTIONS_NUMBER = 16
    }
}

