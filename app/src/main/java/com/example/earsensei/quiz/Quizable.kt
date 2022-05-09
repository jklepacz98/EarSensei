package com.example.earsensei.quiz

interface Quizable {
    fun getAnswers(): Array<String>
    fun getCorrectAnswer(): String
    fun checkAnswer(answer : String) : Boolean
}