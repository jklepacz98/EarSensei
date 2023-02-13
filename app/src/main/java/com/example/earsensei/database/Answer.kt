package com.example.earsensei.database

data class Answer(
    val name: String,
    val correctAnswer: String,
    val stringResourceId: Int,
    var isCorrect: Boolean = correctAnswer == name,
    var isHighlighted: Boolean = false,
)

