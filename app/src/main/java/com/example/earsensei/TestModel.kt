package com.example.earsensei

data class TestModel(
    var id: Int,
    var baseNote: String,
    var correctAnswer : String,
    var userAnswer: String,
    var date: Int,
){}