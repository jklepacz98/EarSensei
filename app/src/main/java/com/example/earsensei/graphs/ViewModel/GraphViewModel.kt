package com.example.earsensei.graphs.ViewModel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.earsensei.MusicTerminology
import com.example.earsensei.QuizType
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
                    musicTerminology.musicList.forEach {
                        val ratio = calculateRatio(musicTerminology.quizType, it.name)
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