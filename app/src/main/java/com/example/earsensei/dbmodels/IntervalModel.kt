package com.example.earsensei.dbmodels

data class IntervalModel(
    var id: Int,
    var baseNote: String,
    var correctAnswer : String,
    var userAnswer: String,
    var date: Int,
){}