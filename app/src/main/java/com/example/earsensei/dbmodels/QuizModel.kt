package com.example.earsensei.dbmodels

data class QuizRecordModel(
    var id: Int,
    var quizType : String,
    var baseNote: String,
    var correctAnswer : String,
    var userAnswer: String,
    var date: Int,
){}