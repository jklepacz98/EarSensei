package com.example.earsensei.graphs.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.earsensei.INTERVALS
import com.example.earsensei.database.EarSenseiDatabase
import com.example.earsensei.database.quizResult.QuizResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChartViewModel(app: Application) : AndroidViewModel(app) {
    val db: EarSenseiDatabase by lazy { EarSenseiDatabase.getDataBase(app) }
    var quizResults: MutableLiveData<List<QuizResult>> = MutableLiveData()
    val chartData: MediatorLiveData<LinkedHashMap<Int, Float>> =
        MediatorLiveData<LinkedHashMap<Int, Float>>().apply {
            addSource(quizResults) {
                viewModelScope.launch(Dispatchers.IO) {
                    val map = linkedMapOf<Int, Float>()
                    INTERVALS.values().forEach {
                        val ratio = calculateRatio(it.type, it.name)
                        map.put(it.translation, ratio)
                    }
                    postValue(map)
                }
            }
            // TODO:
            quizResults.postValue(listOf())
        }

    //todo
    suspend fun calculateRatio(type: String, name: String): Float {
        val correctResults = db.resultDao().getCountAllCorrectResults(type, name)
        val allResults = db.resultDao().getCountAllByAnswer(type, name)
        return correctResults.toFloat() / allResults.toFloat()
    }
}