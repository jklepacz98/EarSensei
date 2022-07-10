package com.example.earsensei.intervalsquiz.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IntervalsQuizViewModel() : ViewModel() {

    val progressMax : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val progress : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val answers : LiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
}