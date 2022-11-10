package com.example.earsensei.graphs.ViewModel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earsensei.MusicTerminology
import com.example.earsensei.database.quizResult.QuizResult
import com.example.earsensei.database.quizResult.QuizResultDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GraphViewModel(
    private val resultDao: QuizResultDao,
    private val musicTerminology: MusicTerminology
) : ViewModel() {
    private var quizResults: MutableLiveData<List<QuizResult>> = MutableLiveData()

    val chartData: MediatorLiveData<LinkedHashMap<Int, Float>> =
        MediatorLiveData<LinkedHashMap<Int, Float>>().apply {
            addSource(quizResults) {
                viewModelScope.launch(Dispatchers.IO) {
                    val map = linkedMapOf<Int, Float>()
                    musicTerminology.musicMap.values.forEach {
                        val ratio = calculateRatio(musicTerminology.type, it.name)
                        map.put(it.translation, ratio)
                    }
                    postValue(map)
                }
            }
            // TODO:
            quizResults.postValue(listOf())
        }

    //todo
    fun calculateRatio(type: String, name: String): Float {
        val correctResults = resultDao.getCountCorrect(type, name)
        val allResults = resultDao.getCount(type, name)
        return correctResults.toFloat() / allResults.toFloat()
    }
}