package com.example.earsensei.dbmodels

data class PerfectPitchModel(
    var id: Int,
    var baseNote: String,
    var correctAnswer : String,
    var userAnswer: String,
    var date: Int,
){}