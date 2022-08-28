package com.example.earsensei.database.quizResult

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuizResult(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val quizType: String,
    val baseNote: String,
    val correctAnswer: String,
    val userAnswer: String,
    val date: Int,
    var isCorrect: Boolean = correctAnswer == userAnswer
)