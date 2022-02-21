package com.example.earsensei.quiz

interface Quizable {
    fun createQuiz()
    fun getAnswers(): Array<String>
    fun checkAnswer(answer : String) : Boolean
}