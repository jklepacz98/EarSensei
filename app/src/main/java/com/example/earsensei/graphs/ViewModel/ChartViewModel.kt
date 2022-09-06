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
    val chartData: MediatorLiveData<LinkedHashMap<String, Float>> =
        MediatorLiveData<LinkedHashMap<String, Float>>().apply {
            addSource(quizResults) {
                viewModelScope.launch(Dispatchers.IO) {

                    val map = linkedMapOf<String, Float>()
                    INTERVALS.values().forEach {
                        val correctResults =
                            db.resultDao().getCountAllCorrectResults(it.getType(), it.name)
                        val allResults = db.resultDao().getCountAllByAnswer(it.getType(), it.name)
                        val ratio = correctResults.toFloat() / allResults.toFloat()
                        map.put(it.name, ratio)
                    }
                    postValue(map)
                }
            }
            // TODO:  
            quizResults.postValue(listOf())
        }

}