package com.example.earsensei.quiz

import com.example.earsensei.MusicElement
import com.example.earsensei.database.Answer

class QuizQuestion(
    val answers: List<Answer>,
    val correctAnswer: MusicElement,
    val baseNote: Int,
)