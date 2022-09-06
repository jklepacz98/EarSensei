package com.example.earsensei.database

data class Quiz(
    val baseNote: String,
    val correctAnswer: Answer,
    val answers: List<Answer>
)