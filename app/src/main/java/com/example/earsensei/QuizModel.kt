package com.example.earsensei

data class QuizModel(
    val baseNote: String,
    val correctAnswer: String,
    val questionPool: List<String>
)