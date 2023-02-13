package com.example.earsensei.graphs.ViewModel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earsensei.MusicElements
import com.example.earsensei.QuizType
import com.example.earsensei.database.quizResult.QuizResult
import com.example.earsensei.database.quizResult.QuizResultDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GraphViewModel(
    private val resultDao: QuizResultDao,
    private val musicElements: MusicElements,
) : ViewModel() {
    private var quizResults: MutableLiveData<List<QuizResult>> = MutableLiveData()

    val chartData: MediatorLiveData<LinkedHashMap<Int, Float>> =
        MediatorLiveData<LinkedHashMap<Int, Float>>().apply {
            addSource(quizResults) {
                viewModelScope.launch(Dispatchers.IO) {
                    val map = linkedMapOf<Int, Float>()
                    musicElements.musicList.forEach {
                        val ratio = calculateRatio(musicElements.quizType, it.name)
                        map[it.translation] = ratio
                    }
                    postValue(map)
                }
            }
            // TODO:
            quizResults.postValue(listOf())
        }

    //todo
    private fun calculateRatio(quizType: QuizType, name: String): Float {
        val correctResults = resultDao.getCountCorrect(quizType.name, name)
        val allResults = resultDao.getCount(quizType.name, name)
        return correctResults.toFloat() / allResults.toFloat()
    }
}