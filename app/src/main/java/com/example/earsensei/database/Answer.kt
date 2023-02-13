package com.example.earsensei.database

data class Answer(
    val name: String,
    val correctAnswer: String,
    val stringResourceId: Int,
    val baseNote: String,
    val isCorrect: Boolean = correctAnswer == name,
    val isHighlighted: Boolean,
)

