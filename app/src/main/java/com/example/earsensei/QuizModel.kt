package com.example.earsensei

data class QuizModel(
    val quizType: String,
    val baseNote: String,
    val correctAnswer: String,
    val questionPool: List<String>
)